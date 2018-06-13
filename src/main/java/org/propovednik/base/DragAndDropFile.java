package org.propovednik.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;

public class DragAndDropFile {
    WebDriver driver;

    public DragAndDropFile(WebDriver driver) {

            this.driver = driver;
        }

        public void dropFile(String[] paths, WebElement target){

            for (int i = 0; i < paths.length; i++) {
                File f = new File(paths[i]);
                if (!f.isFile()) {
                    Assert.fail(paths[i] + " is not a valid file path.");
                }
                paths[i] = f.getAbsolutePath();
            }


            final JavascriptExecutor exec = (JavascriptExecutor) driver;

            String targetId = target.getAttribute("id");

            // Add an id if the target doesn't have one
            if (targetId == null || targetId.isEmpty()) {
                targetId = "seleniumDragAndDropInput_target";
                exec.executeScript("sId=function(e, i){e.id = i;};sId(arguments[0], arguments[1]);", target, targetId);
            }

            // Using one input with multiple file separated by \n does not work with Firefox
            // We need to use several input
            StringBuffer createInputs = new StringBuffer();
            // Create form
            createInputs.append("var f=dojo.create('form', {id: 'seleniumDragAndDropInput_form', style: {position: 'fixed', left: 0, top: 0, width:'100px', height: '100px'}}, dojo.body());");
            // Create inputs
            for (int i = 0; i < paths.length; i++) {
                createInputs.append("dojo.create('input', { type: 'file', id: 'seleniumDragAndDropInput" + i + "'}, f);");
            }

            exec.executeScript(createInputs.toString());

            // Then set file for each input

            for (int i = 0; i < paths.length; i++) {
                WebElement input = driver.findElement(By.id("seleniumDragAndDropInput" + i));
                input.sendKeys(paths[i]);
            }

            // Write code to gather all inputs files in one array;
            StringBuffer gatherInputs = new StringBuffer();
            gatherInputs.append("var seleniumDragAndDropFiles = [];");
            // Need to add the item method to stick to FileList API
            gatherInputs.append("seleniumDragAndDropFiles.item = function (i) { return seleniumDragAndDropFiles[i]};");
            for (int i = 0; i < paths.length; i++) {
                gatherInputs.append("seleniumDragAndDropFiles.push(dojo.byId(\"seleniumDragAndDropInput" + i + "\").files[0]);");
            }

            // Init event with our file
            exec.executeScript(
                    gatherInputs.toString()
                            + "var eve=document.createEvent(\"HTMLEvents\");"
                            + "eve.initEvent(\"drop\", true, true);"
                            + "eve.dataTransfer = {files:seleniumDragAndDropFiles};"
                            + "eve.type = \"drop\";document.getElementById('" + targetId + "').dispatchEvent(eve);");

            exec.executeScript("dojo.destroy('seleniumDragAndDropInput_form');");

            if (targetId == "seleniumDragAndDropInput_target") {
                exec.executeScript("document.getElementById('seleniumDragAndDropInput_target').id = null");
            }


        }
    }

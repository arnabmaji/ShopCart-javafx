package io.github.arnabmaji19.model;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class AlertDialog {

    public static void show(StackPane stackPane, String text){
        var layout = new JFXDialogLayout();
        layout.setBody(new Label(text));
        new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.BOTTOM).show();
    }
}

module be.bralion.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports be.bralion.tictactoe.view;
    opens be.bralion.tictactoe.view to javafx.fxml;

    exports be.bralion.tictactoe.controller;
    opens be.bralion.tictactoe.controller to javafx.fxml;


}
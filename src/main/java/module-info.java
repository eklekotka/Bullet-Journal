module cs3500.pa05 {
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.core;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;

  opens cs3500.pa05 to javafx.fxml;

  exports cs3500.pa05;
  exports cs3500.pa05.controller;
  exports cs3500.pa05.model;
  exports cs3500.pa05.view;
  opens cs3500.pa05.controller to javafx.fxml;
  opens cs3500.pa05.model to javafx.fxml;
  exports cs3500.pa05.model.json;
  opens cs3500.pa05.model.json to javafx.fxml;
}
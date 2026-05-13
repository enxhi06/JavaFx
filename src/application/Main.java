package application;










import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException  {
		Rectangle myrec = new Rectangle();
		myrec.setX(10);
		myrec.setY(10);
		myrec.setHeight(20);
		myrec.setWidth(500);
		myrec.setFill(Color.RED);
		Text header = new Text("PIZZA BUILDER");
		header.setFill(Color.WHITE);
		header.setLayoutX(210);
		header.setLayoutY(25);
		Rectangle myrec2 = new Rectangle();
		myrec2.setX(11);
		myrec2.setY(11);
		myrec2.setHeight(20);
		myrec2.setWidth(500);
		myrec2.setFill(Color.RED);
		Text header2 = new Text("Built Your Own");
		header2.setFill(Color.WHITE);
		header2.setLayoutX(210);
		header2.setLayoutY(25);
        Label title = new Label("Welcome to Papa's Pizza!");
        title.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,40));
        title.setTextFill(Color.YELLOW);
       
        FileInputStream myimage = new FileInputStream("C:\\Users\\jonuz\\Downloads\\pizzaaaa.jpg");
        Image myimg = new Image(myimage,400,300,false,false);
        ImageView myview = new ImageView(myimg);
        myview.setX(15);
        myview.setY(15);
        
        // NAME INPUT
        TextField firstName = new TextField();
        firstName.setPromptText("First Name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        HBox nameBox = new HBox(10, firstName, lastName);
        nameBox.setAlignment(Pos.CENTER);

        Label sizeLabel = new Label("What size pizza would you like?");
        sizeLabel.setTextFill(Color.YELLOW);
        sizeLabel.setFont(Font.font("Ariel",FontWeight.BOLD,FontPosture.REGULAR,20));
        ComboBox<String> sizeBox = new ComboBox<>();
        sizeBox.getItems().addAll(
                "10\" $10.99",
                "12\" $12.99",
                "14\" $14.99",
                "16\" $16.99"
        );

        
        Label crustLabel = new Label("What type of crust do you want?");
        crustLabel.setTextFill(Color.YELLOW);
        crustLabel.setFont(Font.font("Ariel",FontWeight.BOLD,FontPosture.REGULAR,15));
        RadioButton hand = new RadioButton("Hand-tossed");
        RadioButton thin = new RadioButton("Thin-crust");
        RadioButton deep = new RadioButton("Deep-dish");

        ToggleGroup crustGroup = new ToggleGroup();
        hand.setToggleGroup(crustGroup);
        thin.setToggleGroup(crustGroup);
        deep.setToggleGroup(crustGroup);

        VBox crustBox = new VBox(5, crustLabel, hand, thin, deep);

     Label choose = new Label("Choose from:");
     choose.setTextFill(Color.YELLOW);
     choose.setFont(Font.font("Ariel",FontWeight.BOLD,FontPosture.REGULAR,15));
        Label toppingLabel = new Label("All pizzas come with cheese.\nAdditional toppings are $1.25 each.");

        CheckBox pep = new CheckBox("Pepperoni");
        CheckBox sausage = new CheckBox("Sausage");
        CheckBox onion = new CheckBox("Onion");
        CheckBox mushroom = new CheckBox("Mushroom");

        VBox toppingBox = new VBox(5, choose,toppingLabel, pep, sausage, onion, mushroom);

        Button orderBtn = new Button("Place Order");
        orderBtn.setStyle("-fx-font-size: 16px; -fx-background-color: #ff9900;");

        orderBtn.setOnAction(e -> {

           
            String fName = firstName.getText().trim();
            String lName = lastName.getText().trim();

           
            String sizeSelection = sizeBox.getValue();
            if (sizeSelection == null) {
                JOptionPane.showMessageDialog(null, "Please choose a pizza size.");
                return;
            }

            double basePrice = 0;
            if (sizeSelection.contains("10")) basePrice = 10.99;
            else if (sizeSelection.contains("12")) basePrice = 12.99;
            else if (sizeSelection.contains("14")) basePrice = 14.99;
            else if (sizeSelection.contains("16")) basePrice = 16.99;

         
            RadioButton selectedCrust = (RadioButton) crustGroup.getSelectedToggle();
            if (selectedCrust == null) {
                JOptionPane.showMessageDialog(null, "Please choose a crust type.");
                return;
            }
            String crust = selectedCrust.getText();

           
            StringBuilder toppingsList = new StringBuilder();
            int toppingCount = 0;

            if (pep.isSelected()) { toppingsList.append("Pepperoni\n"); toppingCount++; }
            if (sausage.isSelected()) { toppingsList.append("Sausage\n"); toppingCount++; }
            if (onion.isSelected()) { toppingsList.append("Onion\n"); toppingCount++; }
            if (mushroom.isSelected()) { toppingsList.append("Mushroom\n"); toppingCount++; }

            double toppingsCost = toppingCount * 1.25;

          
            double subtotal = basePrice + toppingsCost;
            double tax = subtotal * 0.08875;
            double total = subtotal + tax;

       
            String receipt =
                    "Welcome to Enxhi's Pizza!\n\n" +
                    fName + " " + lName + ", your order is as follows:\n\n" +
                    sizeSelection + " - " + crust + " crust: $" + String.format("%.2f", basePrice) + "\n" +
                    (toppingCount > 0 ? ("Toppings:\n" + toppingsList.toString()) : "No additional toppings\n") +
                    "\nSubtotal: $" + String.format("%.2f", subtotal) +
                    "\nTax (8.875%): $" + String.format("%.2f", tax) +
                    "\n------------------------" +
                    "\nTotal: $" + String.format("%.2f", total) +
                    "\n\n*Your order will be ready in 30 minutes*";

           
            System.out.println(receipt);

            JOptionPane.showMessageDialog(null, receipt);
        });
        Group mygr = new Group(myrec, header);
        Group mygr2 = new Group(myrec2, header2);
        Group mygr3 = new Group(myview);
       
        HBox optionsRow = new HBox(40, crustBox, toppingBox);
        optionsRow.setAlignment(Pos.CENTER);
        
        VBox layout = new VBox(15,mygr,mygr2, 
        		title, 
        		nameBox,
               mygr3, 
               sizeLabel, 
               sizeBox, optionsRow, 
               orderBtn);
		layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        Group content = new Group(layout); 

        ScrollBar mysc = new ScrollBar();
        mysc.setMin(0);
        mysc.setMax(800);       
        mysc.setValue(0);
        mysc.setLayoutX(650);    
        mysc.setLayoutY(0);
        mysc.setPrefHeight(800);
        mysc.setOrientation(Orientation.VERTICAL);

        mysc.valueProperty().addListener((obs, oldVal, newVal) -> {
            content.setLayoutY(-newVal.doubleValue());
        });

        Group root = new Group(content, mysc);
        Scene scene = new Scene(root, 700, 800);
        scene.setFill(Color.LIGHTGREY);
        primaryStage.setTitle("PizzaOrder2");
        primaryStage.setScene(scene);
        primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}


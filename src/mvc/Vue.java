package mvc;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/*
 * Dans cette classe nous definissons les éléments graphiques de notre
 * application.
 * 
 * NB: voir aussi lignes 64-74!
 */
public class Vue extends Application {

	private static Controleur controleur[] = new Controleur[2];
	private static int i = 0;

	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = new BorderPane();

			Scene scene = new Scene(root, 400, 200);

			VBox boite1 = new VBox();
			VBox boite2 = new VBox();

			HBox centre = new HBox();

			Text textValeur1 = new Text("Appuyer sur un bouton");
			Text textValeur2 = new Text("Appuyer sur un bouton");

			final ToggleGroup commutateur = new ToggleGroup();

			RadioButton compteur1 = new RadioButton("Compteur 1");
			RadioButton compteur2 = new RadioButton("Compteur 2");

			compteur1.setToggleGroup(commutateur);
			compteur1.setSelected(true);
			compteur2.setToggleGroup(commutateur);

			boite1.getChildren().add(compteur1);
			boite1.getChildren().add(textValeur1);
			boite1.setAlignment(Pos.CENTER);

			boite2.getChildren().add(compteur2);
			boite2.getChildren().add(textValeur2);
			boite2.setAlignment(Pos.CENTER);

			centre.getChildren().add(boite1);
			centre.getChildren().add(boite2);
			centre.setAlignment(Pos.CENTER);

			Button inc = new Button("+1");
			Button dub = new Button("*2");
			Button div = new Button("/2");
			Button dec = new Button("-1");

			BorderPane.setAlignment(inc, Pos.CENTER);
			BorderPane.setAlignment(dub, Pos.CENTER);
			BorderPane.setAlignment(div, Pos.CENTER);
			BorderPane.setAlignment(dec, Pos.CENTER);
			BorderPane.setAlignment(centre, Pos.CENTER);

			root.setTop(inc);
			root.setBottom(dec);
			root.setLeft(dub);
			root.setRight(div);
			root.setCenter(centre);

			// Le controleur manipule tout evenement.

			inc.setOnAction((action) -> {
				controleur[i].inc();
			});

			dec.setOnAction((action) -> {
				controleur[i].dec();
			});

			dub.setOnAction((action) -> {
				controleur[i].dub();
			});

			div.setOnAction((action) -> {
				controleur[i].div();
			});

 			commutateur.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
				 if(commutateur.getSelectedToggle() == compteur1){
					 i = 0;
				 }else{
					 i = 1;
				 }
			 });

			/*
			 * En raison de la conception des applications JavaFX, nous sommes obligés de
			 * créer le modèle et le controleur ici.
			 * 
			 * Notez cependant que nous passons l'instance du modèle directement dans le
			 * constructeur du controleur; nous n'y avons pas d'autre accès.
			 * 
			 * Pour faciliter les choses, ici le constructeur ne prend pas la Vue entière,
			 * mais juste le sous-ensemble de la Vue (l'objet Text) qu'il doit manipuler.
			 */
			controleur[0] = new Controleur(new Modele(), textValeur1);
			controleur[1] = new Controleur(new Modele(), textValeur2);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

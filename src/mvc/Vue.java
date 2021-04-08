package mvc;

import javafx.application.Application;
import javafx.geometry.Pos;
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

	private static Controleur controleur;

	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 200, 200);

			Text textValeur = new Text("Appuyer sur un bouton");

			Button inc = new Button("+1");
			Button dub = new Button("*2");
			Button div = new Button("/2");
			Button dec = new Button("-1");

			BorderPane.setAlignment(inc, Pos.CENTER);
			BorderPane.setAlignment(dub, Pos.CENTER);
			BorderPane.setAlignment(div, Pos.CENTER);
			BorderPane.setAlignment(dec, Pos.CENTER);

			root.setTop(inc);
			root.setBottom(dec);
			root.setLeft(dub);
			root.setRight(div);
			root.setCenter(textValeur);

			// Le controleur manipule tout evenement.

			inc.setOnAction((action) -> {
				controleur.inc();
			});

			dec.setOnAction((action) -> {
				controleur.dec();
			});

			dub.setOnAction((action) -> {
				controleur.dub();
			});

			div.setOnAction((action) -> {
				controleur.div();
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
			controleur = new Controleur(new Modele(), textValeur);

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

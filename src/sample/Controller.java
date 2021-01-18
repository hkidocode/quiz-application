package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    private Label scoreResult;
    @FXML
    private Label timerLabel;
    @FXML
    private Label questionID;
    @FXML
    private Label levelID;
    @FXML
    private Label questionBody;
    @FXML
    private Button answer1;
    @FXML
    private Button answer2;
    @FXML
    private Button answer3;

    public int incrementIndex = 0;
    public int score = 0;
    public int remainingSeconds = 15;

    String[] questions = {
            "JAVA est un langage",
            "Toutes les classes héritent de la classe",
            "Par convention une classe",
            "Est-ce que on peut avoir plusieurs constructeurs pour la même classe",
            "Dans la ligne \"public class A implements B\" \n, B est",
            "Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecodeQuelle est l’extension du programme en bytecode",
            "Class Test{\nPublic Test () {\nSystem.out.println(”Bonjour”);}\npublic Test (int i) {\nthis();\nSystem.out.println(”Nous sommes en ”+i+ ” !”);};\n}\nqu’affichera l’instruction suivante?\nTest t1=new Test (2018);\"",
            "Voici un constructeur de la classe Employee, y-a t'il un problème ?\nPublic void Employe(String n){\nNom=n;}",
            "Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé",
            "Dans une classe, on accède à ses variables grâce au mot clé",
            "calculerSalaire(int)\ncalculerSalaire(int, double)\nLa méthode calculerSalaire est",
            "Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite",
            "Est-ce qu’une classe peut implémenter plusieurs interfaces ?",
            "La déclaration d'une méthode suivante :\npublic void traitement() throws IOException\nprécise que la méthode propage une exception contrôlée",
            "class Test{\npublic static void main (String[] args) {\ntry {\nint a =10;\nSystem.out.println (\"a = \" + a );\nint b = 0 / a;\nSystem.out.println (\"b = \" + b);\n}\ncatch(ArithmeticException e)\n{System.out.println (\"diviser par 0!\"); }\nfinally\n{System.out.println (\"je suis à l’intérieur de\nfinally\");}\n}\n}"
    };


    String[][] answers = {
            {"Compilé", "Interprété", "Compilé et interpreté"},
            {"Main", "Object", "AWT"},
            {"est en minuscule", "commence par un majuscule", "est en majuscule"},
            {"oui", "non"},
            {"Interface", "Classe parent", "Package"},
            {"aucun des choix", ".java", ".class"},
            {"aucun des choix", "Bonjour Nous sommes en 2018!", "Nous sommes en 2018!"},
            {"vrai", "faux"},
            {"aucun des choix", "final", "const"},
            {"aucun des choix", "this", "super"},
            {"aucun des choix", "surchargéé", "redéfinie"},
            {"oui", "non"},
            {"vrai", "faux"},
            {"vrai", "faux"},
            {"aucun des choix", "a=10\nb=0\nJe suis à l'intérieur\n de finally", "a=10\nb=0\ndivise par 0!\nJe suis à l'intérieur\n de finally"}
    };

    String[] correctAnswers = {
            "Compilé", "Object", "commence par un majuscule", "oui", "Interface",
            ".class", "Nous sommes en 2018!", "vrai", "final", "this",
            "surchargéé", "oui", "vrai", "vrai", "a=10\nb=0\ndivise par 0!\nJe suis à l'intérieur\n de finally"
    };


    Question question1 = new Question(1, 1, questions[0], answers[0],  correctAnswers[0]);
    Question question2 = new Question(2, 1, questions[1], answers[1],  correctAnswers[1]);
    Question question3 = new Question(3, 1, questions[2], answers[2],  correctAnswers[2]);
    Question question4 = new Question(4, 1, questions[3], answers[3],  correctAnswers[3]);
    Question question5 = new Question(5, 1, questions[4], answers[4],  correctAnswers[4]);
    Question question6 = new Question(1, 2, questions[5], answers[5],  correctAnswers[5]);
    Question question7 = new Question(2, 2, questions[6], answers[6],  correctAnswers[6]);
    Question question8 = new Question(3, 2, questions[7], answers[7],  correctAnswers[7]);
    Question question9 = new Question(4, 2, questions[8], answers[8],  correctAnswers[8]);
    Question question10 = new Question(5, 2, questions[9], answers[9],  correctAnswers[9]);
    Question question11 = new Question(1, 3, questions[10], answers[10],  correctAnswers[10]);
    Question question12 = new Question(2, 3, questions[11], answers[11],  correctAnswers[11]);
    Question question13 = new Question(3, 3, questions[12], answers[12],  correctAnswers[12]);
    Question question14 = new Question(4, 3, questions[13], answers[13],  correctAnswers[13]);
    Question question15 = new Question(5, 3, questions[14], answers[14],  correctAnswers[14]);

    Question[] questionsArray = {
            question1, question2, question3, question4, question5,
            question6, question7, question8, question9, question10,
            question11, question12, question13, question14, question15
    };

    private void returnBtnBackground() {
        answer1.setStyle("-fx-background-color: #D3D3D3");
        answer2.setStyle("-fx-background-color: #D3D3D3");
        answer3.setStyle("-fx-background-color: #D3D3D3");
    }


    @FXML
    private void handleClickEvent(ActionEvent actionEvent) throws IOException {
        // Get which button is clicked
        Button buttonText = ((Button) actionEvent.getSource());
        if (buttonText.getText().equals(correctAnswers[incrementIndex])) {
            score += 20;
            scoreResult.setText(score + "");
            buttonText.setStyle("-fx-background-color: #00FF00");
        } else {
            buttonText.setStyle("-fx-background-color: #FF0000");
        }
        if (incrementIndex == 4 || incrementIndex == 9) {
            score = 0;
            scoreResult.setText(score + "");
        }
        incrementIndex++;
        if ((incrementIndex == 4 && score < 40)
                || (incrementIndex == 9 && score < 60)) {
            Parent losePage = FXMLLoader.load(getClass().getResource("fail.fxml"));
            Scene failScene = new Scene(losePage);
            Stage failWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            failWindow.setScene(failScene);
            failWindow.show();
            return;
        }

        if (incrementIndex == 15) {
            if (score >= 80) {
                Parent losePage = FXMLLoader.load(getClass().getResource("win.fxml"));
                Scene failScene = new Scene(losePage);
                Stage failWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                failWindow.setScene(failScene);
                failWindow.show();
            } else {
                Parent losePage = FXMLLoader.load(getClass().getResource("fail.fxml"));
                Scene failScene = new Scene(losePage);
                Stage failWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                failWindow.setScene(failScene);
                failWindow.show();
            }
            return;
        }
        nextQuestion(incrementIndex);
    }

    private void nextQuestion(int index) {
        returnBtnBackground();
        questionBody.setText(questionsArray[index].getQuestionBody());
        questionID.setText(questionsArray[index].getId() + "");
        levelID.setText(questionsArray[index].getLevelID() + "");
        if (answers[index].length == 3) {
            answer3.setVisible(true);
            answer1.setText(answers[index][0]);
            answer2.setText(answers[index][1]);
            answer3.setText(answers[index][2]);
        } else {
            answer1.setText(answers[index][0]);
            answer2.setText(answers[index][1]);
            answer3.setText("");
            answer3.setVisible(false);
        }
    }

    public void setScene(String sceneName) {

    }

}

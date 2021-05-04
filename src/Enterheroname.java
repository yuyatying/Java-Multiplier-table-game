import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
public class Enterheroname {
    public static void display(String title, String message,int id){
        Stage window=new Stage();
        Stage pop=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        pop.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(720);
        window.setMaxWidth(720);
        window.setMinHeight(200);
        window.setMaxHeight(200);
        pop.setMinWidth(720);
        Label label=new Label();
        label.setText(message);
        VBox layout=new VBox(10);
        layout.setAlignment(Pos.CENTER);
        VBox popout=new VBox(10);
        if (id==1) {
            Button closeButton=new Button("Enter");
            TextField heroname=new TextField();
            closeButton.setOnAction(e -> {
                child.hero=new Hero(heroname.getText());
                window.close();
            });
            layout.getChildren().addAll(label,heroname,closeButton);

        }
        if (id==2) {
            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                window.close();
            });
            layout.getChildren().addAll(label,closeButton);

        }
        if (id==3){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }
        if (id==4){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Block the Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }

        if (id==5){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }
        if (id==6){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Block the Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }
        if (id==7){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }
        if (id==8){
            int Q=child.GenQ();
            Button closeButton=new Button("Enter");
            TextField answer=new TextField();
            window.setTitle("Answer Correctly to Block the Attack!");
            label.setText(child.table[Q]);
            closeButton.setOnAction(e -> {
                gameGUI(pop,popout,Q,answer.getText(),id);
                window.close();
            });
            layout.getChildren().addAll(label,answer,closeButton);
        }
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public static VBox gameGUI(Stage pop,VBox layout,int Q,String answer,int id){
        boolean success = child.CheckAns(Q, answer);
        if (success&&id==3) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            child.currectMonster.setHealth(child.hero.getstr());
            if (child.currectMonster.getHealth()<=0){
                label.setText(child.currectMonster.getName()+" has been slain!\n"+child.hero.getName()+
                        " has received "+child.currectMonster.getGold()+" gold!");
                child.hero.setGold(child.currectMonster.getGold());
                GUI.killed=true;
            }
            else {
                label.setText(child.hero.getName() + " has dealt " + child.hero.getstr() + " damage to " + child.currectMonster.getName() + "!");
            }
            GUI.skulllayout.getChildren().remove(GUI.monsterhp);
            GUI.monsterhp.setText(child.currectMonster.getName()+"'s Health\n:"+child.currectMonster.getHealth());
            GUI.skulllayout.getChildren().add(GUI.monsterhp);
            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (success&&id==5) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            child.currectMonster.setHealth(child.hero.getstr());
            if (child.currectMonster.getHealth()<=0){
                label.setText(child.currectMonster.getName()+" has been slain!\n"+child.hero.getName()+
                        " has received "+child.currectMonster.getGold()+" gold!");
                child.hero.setGold(child.currectMonster.getGold());
                GUI.killed=true;
            }
            else {
                label.setText(child.hero.getName() + " has dealt " + child.hero.getstr() + " damage to " + child.currectMonster.getName() + "!");
            }
            GUI.dragonlayout.getChildren().remove(GUI.monsterhp);
            GUI.monsterhp.setText(child.currectMonster.getName()+"'s Health\n: "+child.currectMonster.getHealth());
            GUI.dragonlayout.getChildren().add(GUI.monsterhp);
            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (success&&id==7) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            child.currectMonster.setHealth(child.hero.getstr());
            if (child.currectMonster.getHealth()<=0){
                label.setText(child.currectMonster.getName()+" has been slain!\n"+child.hero.getName()+
                        " has received "+child.currectMonster.getGold()+" gold!");
                child.hero.setGold(child.currectMonster.getGold());
                GUI.killed=true;
            }
            else {
                label.setText(child.hero.getName() + " has dealt " + child.hero.getstr() + " damage to " + child.currectMonster.getName() + "!");
            }
            GUI.demonlayout.getChildren().remove(GUI.monsterhp);
            GUI.monsterhp.setText(child.currectMonster.getName()+"'s Health\n: "+child.currectMonster.getHealth());
            GUI.demonlayout.getChildren().add(GUI.monsterhp);
            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (success&&id==4) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            label.setText("You have successfully blocked the attack from "+ child.currectMonster.getName()+"!");

            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (success&&id==6) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            label.setText("You have successfully blocked the attack from "+ child.currectMonster.getName()+"!");

            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (success&&id==8) {
            GUI.save.writeFile(1);
            pop.setTitle("Success!");
            Label label=new Label();
            label.setText("You have successfully blocked the attack from "+ child.currectMonster.getName()+"!");

            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            layout.getChildren().addAll(label,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        if (!success){
            if (id==3) {
                GUI.save.writeFile(0);
                pop.setTitle("Attack Failed...");
            }
            if (id==5) {
                GUI.save.writeFile(0);
                pop.setTitle("Attack Failed...");
            }
            if (id==7) {
                GUI.save.writeFile(0);
                pop.setTitle("Attack Failed...");
            }
            if (id==4){
                GUI.save.writeFile(0);
                pop.setTitle("Block Failed...");
                child.hero.setHealth(child.currectMonster.getStr());
                GUI.skulllayout.getChildren().remove(GUI.herohp);
                GUI.herohp.setText(child.hero.getName()+"'s Health:"+child.hero.getHealth());
                GUI.skulllayout.getChildren().add(GUI.herohp);
            }
            if (id==6){
                GUI.save.writeFile(0);
                pop.setTitle("Block Failed...");
                child.hero.setHealth(child.currectMonster.getStr());
                GUI.dragonlayout.getChildren().remove(GUI.herohp);
                GUI.herohp.setText(child.hero.getName()+"'s Health:"+child.hero.getHealth());
                GUI.dragonlayout.getChildren().add(GUI.herohp);
            }
            if (id==8){
                GUI.save.writeFile(0);
                pop.setTitle("Block Failed...");
                child.hero.setHealth(child.currectMonster.getStr());
                GUI.demonlayout.getChildren().remove(GUI.herohp);
                GUI.herohp.setText(child.hero.getName()+"'s Health:"+child.hero.getHealth());
                GUI.demonlayout.getChildren().add(GUI.herohp);
            }
            Button closeButton=new Button("Close");
            closeButton.setOnAction(e -> {
                pop.close();
            });
            if (id==4){
                Label label=new Label();
                if (child.hero.getHealth()<=0){
                    label.setText("YOU ARE DEAD...");
                    GUI.dead=true;
                }
                else {
                    label.setText("Received " + child.currectMonster.getStr() + " damage...");
                }
                layout.getChildren().addAll(label);
            }
            if (id==6){
                Label label=new Label();
                if (child.hero.getHealth()<=0){
                    label.setText("YOU ARE DEAD...");
                    GUI.dead=true;
                }
                else {
                    label.setText("Received " + child.currectMonster.getStr() + " damage...");
                }
                layout.getChildren().addAll(label);
            }
            if (id==8){
                Label label=new Label();
                if (child.hero.getHealth()<=0){
                    label.setText("YOU ARE DEAD...");
                    GUI.dead=true;
                }
                else {
                    label.setText("Received " + child.currectMonster.getStr() + " damage...");
                }
                layout.getChildren().addAll(label);
            }
            layout.getChildren().addAll(closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene=new Scene(layout);
            pop.setScene(scene);
            pop.show();
        }
        return layout;
    }
}

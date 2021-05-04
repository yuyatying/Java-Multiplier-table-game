import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GUI extends Application {
    static save save=new save();
    static readfile read;
    private Scene mainscene;
    private Scene streetscene;
    private Scene pchartscene;
    private Scene shopsene;
    private Scene challengescene;
    private Scene skullscene;
    private Scene dragonscene;
    private Scene demonscene;
    private Scene statsscene;
    private Scene itemscene;
    private int lasthp;
    static Label monsterhp;
    static Label herohp;
    static Pane skulllayout;
    static Pane dragonlayout;
    static Pane demonlayout;
    static PieChart.Data Right;
    static PieChart.Data notRight;
    private int count;
    static boolean killed=false;
    static boolean dead=false;
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage Game){
        final int width = 1080;
        final int height = 720;
        read=new readfile();
        read.openFile();
        read.readFile();
        read.closeFile();
        Right=new PieChart.Data("Correct",readfile.correct);
        notRight=new PieChart.Data("Wrong",readfile.wrong);
        ObservableList<PieChart.Data> pieData=FXCollections.observableArrayList(
            Right,notRight
        );
        PieChart pieChart=new PieChart(pieData);
        pieChart.setTitle("Total answered questions: "+readfile.total);
        //Images
        Image title=new Image("Images/Title.png");
        Image street=new Image("Images/street.jpg");
        ImageView multableimg=new ImageView(new Image("Images/multable.jpg"));
        ImageView shopimg=new ImageView(new Image("Images/shop.jpg"));
        ImageView heal=new ImageView(new Image("Images/heal.png"));
        ImageView sword=new ImageView(new Image("Images/sword.jpg"));
        ImageView multablesmall=new ImageView(new Image("Images/multablesmall.jpg"));

        //
        //Background
        BackgroundImage mainBGImg=new BackgroundImage(title,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        BackgroundImage streetBGImg=new BackgroundImage(street,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        //
        //multable
        Pane pchartlayout= new Pane();

        Button extractButton=new Button("Extract data as picture");
        extractButton.setLayoutX(180);
        extractButton.setLayoutY(440);
        extractButton.setOnAction(o->{
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            WritableImage image = pchartlayout.snapshot(new SnapshotParameters(), null);

            File file = new File(dateFormat.format(date) + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                Enterheroname.display("Success!","Data Successfully Extracted!",2);
            }catch (IOException q) {
            }
        });

        Button mainmenuButton=new Button("Exit to mainmenu");
        mainmenuButton.setLayoutX(180);
        mainmenuButton.setLayoutY(400);
        mainmenuButton.setOnAction(e->{
            Game.setScene(mainscene);
        });
        Label c=new Label();
        c.setTextFill(Color.RED);
        c.setText("Correct: "+readfile.correct);
        c.setFont(Font.font("Verdana",26));
        c.setLayoutX(500);
        c.setLayoutY(20);
        Label w=new Label();
        w.setTextFill(Color.RED);
        w.setText("Wrong: "+readfile.wrong);
        w.setFont(Font.font("Verdana",26));
        w.setLayoutX(500);
        w.setLayoutY(60);
        Label percentage=new Label();
        percentage.setTextFill(Color.RED);
        DecimalFormat df=new DecimalFormat("##.##%");
        double percent=((double)readfile.correct/(double)readfile.total);
        String formattedPercent=df.format(percent);
        percentage.setText("Accuracy: "+formattedPercent);
        percentage.setFont(Font.font("Verdana",26));
        percentage.setLayoutX(500);
        percentage.setLayoutY(100);
        Label average=new Label();
        average.setTextFill(Color.RED);
        average.setText("The average accuracy of all kids: 66.72%");
        average.setFont(Font.font("Verdana",26));
        average.setLayoutX(500);
        average.setLayoutY(140);
        pchartlayout.getChildren().addAll(extractButton,c,w,percentage,average,pieChart,mainmenuButton);

        pchartscene=new Scene(pchartlayout,width,height);

        //
        //Buttons
        Button newgameButton=new Button("New Game");
        newgameButton.setLayoutX(480);
        newgameButton.setLayoutY(400);
        newgameButton.setOnAction(e -> {
            save.openFile();
            child.InitTable();
            String hn="Enter Hero Name: ";
            Enterheroname.display("Create Your Hero",hn,1);
            Button adventureButton = new Button("Challenge mobs");
            adventureButton.setLayoutX(800);
            adventureButton.setLayoutY(140);
            Button shopButton=new Button("Shop");
            shopButton.setLayoutX(800);
            shopButton.setLayoutY(180);
            Button checkstatsButton = new Button("Check own's status");
            checkstatsButton.setLayoutX(800);
            checkstatsButton.setLayoutY(220);
            Button menuButton = new Button("Save & Quit");
            menuButton.setLayoutX(800);
            menuButton.setLayoutY(260);
            menuButton.setOnAction(x->{
                save.closeFile();
                Game.setScene(mainscene);
            });
            Pane streetlayout=new Pane();
            streetlayout.setBackground(new Background(streetBGImg));
            streetlayout.getChildren().addAll(adventureButton,shopButton,checkstatsButton,menuButton);
            streetscene=new Scene(streetlayout,width,height);
            //adventureButton
            adventureButton.setOnAction(b->{
                Pane challengelayout=new Pane();
                ImageView skullimg=new ImageView(new Image("Images/skull.jpg"));
                ImageView dragonimg=new ImageView(new Image("Images/dragon.jpg"));
                ImageView demonimg=new ImageView(new Image("Images/demon.jpg"));
                Text stage= new Text("Stages: (Press Picture to Enter)");
                stage.setFont(Font.font("Verdana",40));
                stage.setFill(Color.RED);
                stage.setLayoutX(90);
                stage.setLayoutY(150);
                Button bkButton=new Button("Back to Street");
                bkButton.setLayoutX(470);
                bkButton.setLayoutY(443);
                bkButton.setOnAction(x->{
                    Game.setScene(streetscene);
                });
                Button skullButton=new Button();
                skullButton.setGraphic(skullimg);
                skullButton.setOnAction(a->{
                    child.hero.returnCount();
                    killed=false;
                    dead=false;
                    child.currectMonster=Monster.Skull;
                    herohp=new Label();
                    herohp.setFont(Font.font("Verdana",26));
                    herohp.setTextFill(Color.RED);
                    herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                    herohp.setLayoutX(0);
                    herohp.setLayoutY(650);
                    monsterhp=new Label();
                    monsterhp.setFont(Font.font("Verdana",26));
                    monsterhp.setTextFill(Color.RED);
                    monsterhp.setText(child.currectMonster.getName()+"'s Health:\n"+child.currectMonster.getHealth());
                    monsterhp.setLayoutX(0);
                    monsterhp.setLayoutY(0);
                    Button attack=new Button("Attack");
                    Button item=new Button("Use Items");
                    Button escape=new Button("Escape");
                    ImageView skullbig=new ImageView(new Image("Images/skullbig.jpg"));
                    skulllayout=new Pane();
                    item.setOnAction(useitem->{
                        Pane itemlistslayout=new Pane();
                        Label Quantity = new Label();
                        Quantity.setFont(Font.font("Verdana",26));
                        Quantity.setTextFill(Color.RED);
                        Quantity.setText("Quantity:");
                        Quantity.setLayoutX(220);
                        Quantity.setLayoutY(250);
                        Label HQ=new Label();
                        HQ.setFont(Font.font("Verdana",26));
                        HQ.setTextFill(Color.RED);
                        HQ.setText(child.hero.getHcount()+"");
                        HQ.setLayoutX(400);
                        HQ.setLayoutY(250);
                        Label SQ=new Label();
                        SQ.setFont(Font.font("Verdana",26));
                        SQ.setTextFill(Color.RED);
                        SQ.setText(child.hero.getScount()+"");
                        SQ.setLayoutX(500);
                        SQ.setLayoutY(250);
                        Label PQ=new Label();
                        PQ.setFont(Font.font("Verdana",26));
                        PQ.setTextFill(Color.RED);
                        PQ.setText(child.hero.getPcount()+"");
                        PQ.setLayoutX(600);
                        PQ.setLayoutY(250);
                        Button HPbutton=new Button();
                        Button Sbutton=new Button();
                        Button Pbutton=new Button();
                        Button contbutton=new Button("Back");
                        contbutton.setLayoutX(480);
                        contbutton.setLayoutY(450);
                        contbutton.setOnAction(x->{
                            Game.setScene(skullscene);
                        });
                        HPbutton.setGraphic(new ImageView(new Image("Images/heal.png")));
                        HPbutton.setLayoutX(350);
                        HPbutton.setLayoutY(300);
                        HPbutton.setOnAction(healuse -> {
                            if (child.hero.getHcount()>0) {
                                child.hero.setHcount(1);
                                child.hero.heal(5);
                                Enterheroname.display("Item Used","Healed "+child.hero.getName()+" by 5 hp!",2);
                                skulllayout.getChildren().remove(herohp);
                                herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                                skulllayout.getChildren().add(herohp);
                                child.hero.RemovefromList(Items.Heal);
                            Game.setScene(skullscene);}
                        });
                        Sbutton.setGraphic(new ImageView(new Image("Images/sword.jpg")));
                        Sbutton.setLayoutX(450);
                        Sbutton.setLayoutY(300);
                        Sbutton.setOnAction(sworduse -> {
                            if (child.hero.getScount()>0) {
                                child.hero.setScount(1);
                                Enterheroname.display("Item Used",child.hero.getName()+" has increased 2 strength!",2);
                                child.hero.increaseStr();
                                child.hero.RemovefromList(Items.Sword);
                            Game.setScene(skullscene);}
                        });
                        Pbutton.setGraphic(new ImageView(new Image("Images/multablesmall.jpg")));
                        Pbutton.setLayoutX(550);
                        Pbutton.setLayoutY(300);
                        Pbutton.setOnAction(peekuse -> {
                            if (child.hero.getPcount()>0){
                                child.hero.setPcount(1);
                            Pane peeklayout = new Pane();
                            Button closebutton = new Button("Close Table");
                            closebutton.setLayoutX(600);
                            closebutton.setLayoutY(140);
                            closebutton.setOnAction(j -> {
                                Game.setScene(skullscene);
                            });
                            ImageView tableimg = new ImageView(new Image("Images/multable.jpg"));
                            peeklayout.getChildren().addAll(tableimg, closebutton);
                            peeklayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                            Scene peekscene = new Scene(peeklayout, width, height);
                            child.hero.RemovefromList(Items.Peek);
                            Game.setScene(peekscene);
                        }});
                        itemlistslayout.getChildren().addAll(Quantity,HQ,SQ,PQ,contbutton,HPbutton,Sbutton,Pbutton);
                        itemlistslayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                        itemscene= new Scene(itemlistslayout,width,height);
                        Game.setScene(itemscene);
                    });
                    skulllayout.getChildren().addAll(herohp,monsterhp,skullbig,attack,item,escape);
                    attack.setLayoutX(420);
                    attack.setLayoutY(650);
                    attack.setOnAction(attackskull->{
                        if (killed){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        if (dead){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        else if(!killed){
                            lasthp = child.currectMonster.getHealth();
                            Enterheroname.display("", "", 4);
                            Enterheroname.display("", "", 3);
                        }
                    });
                    item.setLayoutX(490);
                    item.setLayoutY(650);
                    escape.setLayoutX(585);
                    escape.setLayoutY(650);
                    skullbig.setLayoutX(311);
                    skullbig.setLayoutY(0);
                    skulllayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                    skullscene=new Scene(skulllayout,width,height);
                    escape.setOnAction(x->{
                        child.currectMonster.Regen(child.currectMonster);
                        child.hero.Regen();
                        Game.setScene(streetscene);
                    });
                    Game.setScene(skullscene);
                });
                Button dragonButton=new Button();
                dragonButton.setGraphic(dragonimg);
                dragonButton.setOnAction(a->{
                    child.hero.returnCount();
                    killed=false;
                    dead=false;
                    child.currectMonster=Monster.Dragon;
                    herohp=new Label();
                    herohp.setFont(Font.font("Verdana",26));
                    herohp.setTextFill(Color.RED);
                    herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                    herohp.setLayoutX(0);
                    herohp.setLayoutY(650);
                    monsterhp=new Label();
                    monsterhp.setFont(Font.font("Verdana",26));
                    monsterhp.setTextFill(Color.RED);
                    monsterhp.setText(child.currectMonster.getName()+"'s Health:\n"+child.currectMonster.getHealth());
                    monsterhp.setLayoutX(0);
                    monsterhp.setLayoutY(0);
                    Button attack=new Button("Attack");
                    Button item=new Button("Use Items");
                    Button escape=new Button("Escape");
                    ImageView dragonbig=new ImageView(new Image("Images/dragonbig.jpg"));
                    dragonlayout=new Pane();
                    item.setOnAction(useitem->{
                        Pane itemlistslayout=new Pane();
                        Label Quantity = new Label();
                        Quantity.setFont(Font.font("Verdana",26));
                        Quantity.setTextFill(Color.RED);
                        Quantity.setText("Quantity:");
                        Quantity.setLayoutX(220);
                        Quantity.setLayoutY(250);
                        Label HQ=new Label();
                        HQ.setFont(Font.font("Verdana",26));
                        HQ.setTextFill(Color.RED);
                        HQ.setText(child.hero.getHcount()+"");
                        HQ.setLayoutX(400);
                        HQ.setLayoutY(250);
                        Label SQ=new Label();
                        SQ.setFont(Font.font("Verdana",26));
                        SQ.setTextFill(Color.RED);
                        SQ.setText(child.hero.getScount()+"");
                        SQ.setLayoutX(500);
                        SQ.setLayoutY(250);
                        Label PQ=new Label();
                        PQ.setFont(Font.font("Verdana",26));
                        PQ.setTextFill(Color.RED);
                        PQ.setText(child.hero.getPcount()+"");
                        PQ.setLayoutX(600);
                        PQ.setLayoutY(250);
                        Button HPbutton=new Button();
                        Button Sbutton=new Button();
                        Button Pbutton=new Button();
                        Button contbutton=new Button("Back");
                        contbutton.setLayoutX(480);
                        contbutton.setLayoutY(450);
                        contbutton.setOnAction(x->{
                            Game.setScene(dragonscene);
                        });
                        HPbutton.setGraphic(new ImageView(new Image("Images/heal.png")));
                        HPbutton.setLayoutX(350);
                        HPbutton.setLayoutY(300);
                        HPbutton.setOnAction(healuse -> {
                            if (child.hero.getHcount()>0) {
                                child.hero.setHcount(1);
                                child.hero.heal(5);
                                Enterheroname.display("Item Used","Healed "+child.hero.getName()+" by 5 hp!",2);
                                dragonlayout.getChildren().remove(herohp);
                                herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                                dragonlayout.getChildren().add(herohp);
                                child.hero.RemovefromList(Items.Heal);
                                Game.setScene(dragonscene);}
                        });
                        Sbutton.setGraphic(new ImageView(new Image("Images/sword.jpg")));
                        Sbutton.setLayoutX(450);
                        Sbutton.setLayoutY(300);
                        Sbutton.setOnAction(sworduse -> {
                            if (child.hero.getScount()>0) {
                                child.hero.setScount(1);
                                child.hero.increaseStr();
                                Enterheroname.display("Item Used",child.hero.getName()+" has increased 2 strength!",2);
                                child.hero.RemovefromList(Items.Sword);
                                Game.setScene(dragonscene);}
                        });
                        Pbutton.setGraphic(new ImageView(new Image("Images/multablesmall.jpg")));
                        Pbutton.setLayoutX(550);
                        Pbutton.setLayoutY(300);
                        Pbutton.setOnAction(peekuse -> {
                            if (child.hero.getPcount()>0){
                                child.hero.setPcount(1);
                                Pane peeklayout = new Pane();
                                Button closebutton = new Button("Close Table");
                                closebutton.setLayoutX(600);
                                closebutton.setLayoutY(140);
                                closebutton.setOnAction(j -> {
                                    Game.setScene(dragonscene);
                                });
                                ImageView tableimg = new ImageView(new Image("Images/multable.jpg"));
                                peeklayout.getChildren().addAll(tableimg, closebutton);
                                peeklayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                                Scene peekscene = new Scene(peeklayout, width, height);
                                child.hero.RemovefromList(Items.Peek);
                                Game.setScene(peekscene);
                            }});
                        itemlistslayout.getChildren().addAll(Quantity,HQ,SQ,PQ,contbutton,HPbutton,Sbutton,Pbutton);
                        itemlistslayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                        itemscene= new Scene(itemlistslayout,width,height);
                        Game.setScene(itemscene);
                    });
                    dragonlayout.getChildren().addAll(herohp,monsterhp,dragonbig,attack,item,escape);
                    attack.setLayoutX(420);
                    attack.setLayoutY(650);
                    attack.setOnAction(attackdragon->{
                        if (killed){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        if (dead){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        else if(!killed){
                            lasthp = child.currectMonster.getHealth();
                            Enterheroname.display("", "", 6);
                            Enterheroname.display("", "", 5);
                        }
                    });
                    item.setLayoutX(490);
                    item.setLayoutY(650);
                    escape.setLayoutX(585);
                    escape.setLayoutY(650);
                    dragonbig.setLayoutX(311);
                    dragonbig.setLayoutY(0);
                    dragonlayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                    dragonscene=new Scene(dragonlayout,width,height);
                    escape.setOnAction(x->{
                        child.currectMonster.Regen(child.currectMonster);
                        child.hero.Regen();
                        Game.setScene(streetscene);
                    });
                    Game.setScene(dragonscene);
                });
                Button demonButton=new Button();
                demonButton.setGraphic(demonimg);
                demonButton.setOnAction(a->{
                    child.hero.returnCount();
                    killed=false;
                    dead=false;
                    child.currectMonster=Monster.Demon;
                    herohp=new Label();
                    herohp.setFont(Font.font("Verdana",26));
                    herohp.setTextFill(Color.RED);
                    herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                    herohp.setLayoutX(0);
                    herohp.setLayoutY(650);
                    monsterhp=new Label();
                    monsterhp.setFont(Font.font("Verdana",26));
                    monsterhp.setTextFill(Color.RED);
                    monsterhp.setText(child.currectMonster.getName()+"'s Health:\n"+child.currectMonster.getHealth());
                    monsterhp.setLayoutX(0);
                    monsterhp.setLayoutY(0);
                    Button attack=new Button("Attack");
                    Button item=new Button("Use Items");
                    Button escape=new Button("Escape");
                    ImageView demonbig=new ImageView(new Image("Images/demonbig.jpg"));
                    demonlayout=new Pane();
                    item.setOnAction(useitem->{
                        Pane itemlistslayout=new Pane();
                        Label Quantity = new Label();
                        Quantity.setFont(Font.font("Verdana",26));
                        Quantity.setTextFill(Color.RED);
                        Quantity.setText("Quantity:");
                        Quantity.setLayoutX(220);
                        Quantity.setLayoutY(250);
                        Label HQ=new Label();
                        HQ.setFont(Font.font("Verdana",26));
                        HQ.setTextFill(Color.RED);
                        HQ.setText(child.hero.getHcount()+"");
                        HQ.setLayoutX(400);
                        HQ.setLayoutY(250);
                        Label SQ=new Label();
                        SQ.setFont(Font.font("Verdana",26));
                        SQ.setTextFill(Color.RED);
                        SQ.setText(child.hero.getScount()+"");
                        SQ.setLayoutX(500);
                        SQ.setLayoutY(250);
                        Label PQ=new Label();
                        PQ.setFont(Font.font("Verdana",26));
                        PQ.setTextFill(Color.RED);
                        PQ.setText(child.hero.getPcount()+"");
                        PQ.setLayoutX(600);
                        PQ.setLayoutY(250);
                        Button HPbutton=new Button();
                        Button Sbutton=new Button();
                        Button Pbutton=new Button();
                        Button contbutton=new Button("Back");
                        contbutton.setLayoutX(480);
                        contbutton.setLayoutY(450);
                        contbutton.setOnAction(x->{
                            Game.setScene(demonscene);
                        });
                        HPbutton.setGraphic(new ImageView(new Image("Images/heal.png")));
                        HPbutton.setLayoutX(350);
                        HPbutton.setLayoutY(300);
                        HPbutton.setOnAction(healuse -> {
                            if (child.hero.getHcount()>0) {
                                child.hero.setHcount(1);
                                child.hero.heal(5);
                                Enterheroname.display("Item Used","Healed "+child.hero.getName()+" by 5 hp!",2);
                                demonlayout.getChildren().remove(herohp);
                                herohp.setText(child.hero.getName()+"'s Health: "+child.hero.getHealth());
                                demonlayout.getChildren().add(herohp);
                                child.hero.RemovefromList(Items.Heal);
                                Game.setScene(demonscene);}
                        });
                        Sbutton.setGraphic(new ImageView(new Image("Images/sword.jpg")));
                        Sbutton.setLayoutX(450);
                        Sbutton.setLayoutY(300);
                        Sbutton.setOnAction(sworduse -> {
                            if (child.hero.getScount()>0) {
                                child.hero.setScount(1);
                                child.hero.increaseStr();
                                Enterheroname.display("Item Used",child.hero.getName()+" has increased 2 strength!",2);
                                child.hero.RemovefromList(Items.Sword);
                                Game.setScene(demonscene);}
                        });
                        Pbutton.setGraphic(new ImageView(new Image("Images/multablesmall.jpg")));
                        Pbutton.setLayoutX(550);
                        Pbutton.setLayoutY(300);
                        Pbutton.setOnAction(peekuse -> {
                            if (child.hero.getPcount()>0){
                                child.hero.setPcount(1);
                                Pane peeklayout = new Pane();
                                Button closebutton = new Button("Close Table");
                                closebutton.setLayoutX(600);
                                closebutton.setLayoutY(140);
                                closebutton.setOnAction(j -> {
                                    Game.setScene(demonscene);
                                });
                                ImageView tableimg = new ImageView(new Image("Images/multable.jpg"));
                                peeklayout.getChildren().addAll(tableimg, closebutton);
                                peeklayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                                Scene peekscene = new Scene(peeklayout, width, height);
                                child.hero.RemovefromList(Items.Peek);
                                Game.setScene(peekscene);
                            }});
                        itemlistslayout.getChildren().addAll(Quantity,HQ,SQ,PQ,contbutton,HPbutton,Sbutton,Pbutton);
                        itemlistslayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                        itemscene= new Scene(itemlistslayout,width,height);
                        Game.setScene(itemscene);
                    });
                    demonlayout.getChildren().addAll(herohp,monsterhp,demonbig,attack,item,escape);
                    attack.setLayoutX(420);
                    attack.setLayoutY(650);
                    attack.setOnAction(attackdemon->{
                        if (killed){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        if (dead){
                            Game.setScene(streetscene);
                            child.currectMonster.Regen(child.currectMonster);
                            child.hero.Regen();
                        }
                        else if(!killed){
                            lasthp = child.currectMonster.getHealth();
                            Enterheroname.display("", "", 8);
                            Enterheroname.display("", "", 7);
                        }
                    });
                    item.setLayoutX(490);
                    item.setLayoutY(650);
                    escape.setLayoutX(585);
                    escape.setLayoutY(650);
                    demonbig.setLayoutX(311);
                    demonbig.setLayoutY(0);
                    demonlayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                    demonscene=new Scene(demonlayout,width,height);
                    escape.setOnAction(x->{
                        child.currectMonster.Regen(child.currectMonster);
                        child.hero.Regen();
                        Game.setScene(streetscene);
                    });
                    Game.setScene(demonscene);
                });
                challengelayout.getChildren().addAll(stage,skullButton,dragonButton,demonButton,bkButton);
                skullButton.setLayoutX(90);
                skullButton.setLayoutY(200);
                dragonButton.setLayoutX(390);
                dragonButton.setLayoutY(200);
                demonButton.setLayoutX(690);
                demonButton.setLayoutY(200);
                challengelayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                challengescene=new Scene(challengelayout,width,height);
                Game.setScene(challengescene);
            });
            //
            //shopButton
            shopButton.setOnAction(z->{
                Label guide=new Label();
                guide.setText("Press on the items' picture to buy");
                guide.setFont(Font.font("Verdana",26));
                guide.setTextFill(Color.RED);
                Label gold=new Label();
                gold.setText("Gold: "+child.hero.getGold());
                gold.setFont(Font.font("Verdana",26));
                gold.setTextFill(Color.RED);
                Text healprice=new Text("$3");
                Text swordprice=new Text("$4");
                Text mulprice=new Text("$5");
                Button bkButton=new Button("Back to Street");
                healprice.setFont(Font.font("Verdana",26));
                healprice.setFill(Color.RED);
                swordprice.setFont(Font.font("Verdana",26));
                swordprice.setFill(Color.RED);
                mulprice.setFont(Font.font("Verdana",26));
                mulprice.setFill(Color.RED);
                Pane shoplayout=new Pane();
                Button healButton=new Button();
                healButton.setGraphic(heal);
                Button swordButton=new Button();
                swordButton.setGraphic(sword);
                Button multablesmallButton=new Button();
                multablesmallButton.setGraphic(multablesmall);
                shoplayout.getChildren().addAll(guide,gold,shopimg,healButton,swordButton,multablesmallButton,healprice,swordprice,mulprice,bkButton);
                bkButton.setLayoutX(820);
                bkButton.setLayoutY(420);
                bkButton.setOnAction(v->{
                    Game.setScene(streetscene);
                });
                healButton.setLayoutX(820);
                healButton.setLayoutY(100);
                healButton.setOnAction(buyheal->{
                    int dummy=child.hero.getGold();
                    child.hero.buy(1);
                    shoplayout.getChildren().remove(gold);
                    gold.setText("Gold: "+child.hero.getGold());
                    shoplayout.getChildren().add(gold);
                    if (child.hero.getGold()==dummy){
                        Enterheroname.display("Alert","You do not have enough money!",2);
                    }
                    else {
                        Enterheroname.display("Bought", "You have bought a Healing Potion!",2);
                    }

                });
                swordButton.setLayoutX(820);
                swordButton.setLayoutY(200);
                swordButton.setOnAction(buysword->{
                    int dummy=child.hero.getGold();
                    child.hero.buy(2);
                    shoplayout.getChildren().remove(gold);
                    gold.setText("Gold: "+child.hero.getGold());
                    shoplayout.getChildren().add(gold);
                    if (child.hero.getGold()==dummy){
                        Enterheroname.display("Alert","You do not have enough money!",2);
                    }
                    else {
                        Enterheroname.display("Bought", "You have bought a Sword!",2);
                    }
                });
                multablesmallButton.setLayoutX(820);
                multablesmallButton.setLayoutY(300);
                multablesmallButton.setOnAction(buymul->{
                    int dummy=child.hero.getGold();
                    child.hero.buy(3);
                    shoplayout.getChildren().remove(gold);
                    gold.setText("Gold: "+child.hero.getGold());
                    shoplayout.getChildren().add(gold);
                    if (child.hero.getGold()==dummy){
                        Enterheroname.display("Alert","You do not have enough money!",2);
                    }
                    else {
                        Enterheroname.display("Bought", "You have bought a Multiplication Table!",2);
                    }
                });
                gold.setLayoutX(820);
                gold.setLayoutY(0);
                healprice.setLayoutX(960);
                healprice.setLayoutY(150);
                swordprice.setLayoutX(960);
                swordprice.setLayoutY(260);
                mulprice.setLayoutX(960);
                mulprice.setLayoutY(360);
                guide.setLayoutX(20);
                guide.setLayoutY(580);
              //  multablesmallButton.setStyle("-fx-background-color: black;");
                shoplayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                shopsene= new Scene(shoplayout,width,height);
                Game.setScene(shopsene);
            });
            //
            //checkstatsbutton
            checkstatsButton.setOnAction(x->{
                Text str=new Text("Strength: "+child.hero.getstr());
                Text hp=new Text("Health: "+child.hero.getHealth());
                Text gold=new Text("Gold: "+child.hero.getGold());
                Text item=new Text("Items: "+child.hero.getItems());
                Text name=new Text(child.hero.getName());
                str.setFont(Font.font("Verdana",26));
                str.setFill(Color.RED);
                item.setFont(Font.font("Verdana",26));
                item.setFill(Color.RED);
                hp.setFont(Font.font("Verdana",26));
                hp.setFill(Color.RED);
                gold.setFont(Font.font("Verdana",26));
                gold.setFill(Color.RED);
                name.setFont(Font.font("Verdana",26));
                name.setFill(Color.RED);
                ImageView heroimg=new ImageView(new Image("Images/hero.jpg"));
                Button backButton=new Button("Back to Street");
                backButton.setLayoutX(100);
                backButton.setLayoutY(350);
                hp.setLayoutX(350);
                hp.setLayoutY(50);
                str.setLayoutX(350);
                str.setLayoutY(100);
                gold.setLayoutX(350);
                gold.setLayoutY(150);
                item.setLayoutX(350);
                item.setLayoutY(200);
                name.setLayoutX(100);
                name.setLayoutY(340);
                backButton.setOnAction(y->{
                    Game.setScene(streetscene);});
                Pane statslayout=new Pane();
                statslayout.getChildren().addAll(backButton,heroimg,str,hp,gold,name,item);
                statslayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
                statsscene=new Scene(statslayout,width,height);

                Game.setScene(statsscene);
            });
            //
            Game.setScene(streetscene);
        });
        Button multable=new Button("Check performance/statistics");
        multable.setLayoutX(430);
        multable.setLayoutY(440);
        multable.setOnAction(e ->{
            Game.setScene(pchartscene);
        });

        Button exit=new Button("Exit game");
        exit.setLayoutX(485);
        exit.setLayoutY(480);
        exit.setOnAction(e->{
            System.exit(1);
        });
        //
        //Layout
        Pane mainlayout = new Pane();
        mainlayout.setBackground(new Background(mainBGImg));
        mainlayout.getChildren().addAll(newgameButton,multable,exit);
        //
        //Scenes
        mainscene=new Scene(mainlayout,width,height);
        //
        Game.setTitle("*Adventure");
        Game.setScene(mainscene);
        Game.show();
    }
}

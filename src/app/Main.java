package app;


import Database.DatabaseHelper;
import Entities.Card;
import Entities.CardManager;
import data_access.FileCardDataAccessObject;
import use_case.generatecard.GenerateCardDataAccessInterFace;
import use_case.generatecard.GenerateImageDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

            String Apikey = "api-key";
//        String theme = "slice of cake wearing a brown hat ";
//        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/test.db");
//
//
//        GenerateCardDataAccessInterFace cardex = new GenerateCardDataAccessInterFace(Apikey, theme, cardDAO);
//
//
//        Integer cardID = cardex.generateAndSaveCharacters();
//
//        Card card = cardDAO.getCard(cardID);
//
//        System.out.println(card);
//
//        GenerateImageDataAccessInterface cardop = new GenerateImageDataAccessInterface(Apikey, cardDAO);
//
//        String character = "slice of cake wearing a brown hats";
//
//        String sampleprompt = String.format("Create an action figure card for [Character Name]. The card should feature a detailed illustration of [Character Name]. The background of the card should have thematic elements related to the character's backstory or abilities, like [specific themes or motifs]. Ensure the character's pose is dynamic and fitting for an action figure, and the card design is vivid and eye-catching.", character);
//        String maybe = "The background of the card should have thematic elements related to the character's backstory or abilities, like [specific themes or motifs]. Ensure the character's pose is dynamic and fitting for an action figure, and the card design is vivid and eye-catching.";
//
//        cardop.generateImageForCard(card.getId(), "Create a simple character card of a" + theme );
//
//        cardDAO.exit();
//




//
//        createNewDatabase("test.db");



//        String dbpath = "src/db/test.db";
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(dbpath);
//
//
////        databaseHelper.createTables();
//
//        FileCardDataAccessObject Dao = new FileCardDataAccessObject("src/db/cards.txt","src/db/test.db" );
//
//        CardManager cad = new CardManager( Dao.CardManager());
//
//        ArrayList<Integer> pop  = Dao.CardManager();
//
//
//
//        for (Integer i : pop) {
//            System.out.println(i);
//            Card card = Dao.getCard(i);
//
//            databaseHelper.insertCardIntoSQLite(card);
//
//        }









        FileCardDataAccessObject DAO = new FileCardDataAccessObject("src/db/cards.txt","src/db/cards.db" );

        ArrayList<Integer> Array  = DAO.CardManager();


        for (Integer i: Array){

            Card card = DAO.getCard(i);

            if(Objects.equals(card.getDesc(), "WIP")){
                GenerateCardDataAccessInterFace G_card = new GenerateCardDataAccessInterFace(Apikey ,card.getName() ,DAO);


                card.setDesc(G_card.generateDescription(card.getName()));

                System.out.println(card.getName()+ " Description Generated");

            } else if (Objects.equals(card.getimgpath(), "Not Assigned")) {
                GenerateImageDataAccessInterface G_image = new GenerateImageDataAccessInterface(Apikey , DAO);

                G_image.generateImageForCard(card.getId(), card.getName());

                System.out.println(card.getName() + "Image generated");

            } else if (card.getStats().getRarity() == null) {
                GenerateCardDataAccessInterFace G_card = new GenerateCardDataAccessInterFace(Apikey ,card.getName() ,DAO);

                card.setrarity(G_card.generateRarity());

                System.out.println(card.getName() + " Rarity updated");

                System.out.println(card.getDesc() + "Description");

                System.out.println(card.getimgpath()  + "Path");

            }else {

                System.out.println(card.getStats().getRarity());

                System.out.println("Nothing");

                System.out.println(card.getDesc() + "Description");

                System.out.println(card.getimgpath()  + "Path");


            }

            DAO.exit();

        }








//
//
    }



}
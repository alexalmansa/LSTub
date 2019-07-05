import java.io.FileNotFoundException;

/**
 * @author Alex Almansa, Marc Llort
 *//*
 @param menu: variable que s'encarrega del menu
 @param info: Gestio de la informacio del json
 @param video: Informacio de tots els diferents videos
 @param i: informacio de playlists i channels
 @param json: array on es troba la informacio del json


 */
public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();                         // Menu de l'exercici
        YoutubeData info = new YoutubeData();
        Video video = new Video();
        Info i = new Info();
        ArrayDto json = info.gsonLector();



            while (0 == 0) {

                menu.printMenu();
                menu.comprovaOpcio();
                int opcio = menu.getOpcio();
                String cerca;

                switch (opcio) {

                    case 1:     //cerca resultats

                        cerca = menu.llegeixCerca();

                        info.printaResultats(cerca, 3);


                        break;

                    case 2:     //Desar Preferits

                        cerca = menu.llegeixCerca();

                        info.printaOpcions(cerca, 10, 0);

                        json = info.gsonLector();

                        break;


                    case 3:     //Millors Videos

                        video.printaFavorits(json);


                        break;


                    case 4:     //EstadÃ­stiques

                        i.printaStats(json);

                        break;


                    case 5:     //Llistes de ReproducciÃ³

                        html.playlistHtml(json);

                        break;

                    case 6:     //El Mosaic

                        html.mosaic(json);

                        break;

                }

                if (opcio == 7) {
                    break;
                }


            }

    }

}


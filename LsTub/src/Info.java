import java.util.Date;

/**
 * Classe que s'encarrega de printar la informacio de videos, canals i playlists
 *
 * @author Alex Almansa, Marc Llort
 */
public class Info extends Video {

    /**
     * Funcio que s'encarrega de printar les estadistiques dels videos, canals i playlists que hi ha a faborits
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    public void printaStats(ArrayDto json) {
        try {
            mitjRep(json);
            mitjSubs(json);
            playlistInfo(json);

        } catch (Exception buit) {
            System.out.println("Fitxer buit!");
        }
    }

    /**
     * Funcio que s'encarrega printar la mitjana de reproduccions dels videos que tenim
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    private void mitjRep(ArrayDto json) {

        long mitjana = 0;
        ArrayVideo arrayvideos = new ArrayVideo();

        arrayvideos = buscaInfo(json, 1, 0);                           //guardem videos de jsoinfavoirites en un array
        if (arrayvideos.getItems().size() != 0) {                                         //Ens assegurem que hi ha algun video

            for (int i = 0; i < arrayvideos.getItems().size(); i++) {                                         //fem mitjana iterant sobre el els videos
                mitjana = mitjana + arrayvideos.getItems().get(i).getStatistics().getViewCount();
            }

            System.out.println("Mitjana Reproduccions: " + mitjana / arrayvideos.getItems().size());

        }
    }


    /**
     * Funcio que s'encarrega de printar la mitjana de subscriptors que tenen els canals que hi ha a faborits
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    private void mitjSubs(ArrayDto json) {

        long mitjana = 0;
        ArrayVideo arraycanal = new ArrayVideo();                                       //guardem canals de jsoinfavoirites en un array

        arraycanal = buscaInfo(json, 2, 0);
        if (arraycanal.getItems().size() != 0) {

            for (int i = 0; i < arraycanal.getItems().size(); i++) {                                              //fem mitjana iterant sobre el els canals
                mitjana = mitjana + arraycanal.getItems().get(i).getStatistics().getSubscriberCount();
            }

            System.out.println("Mitjana Subscriptors: " + mitjana / arraycanal.getItems().size());
        }
    }


    /**
     * Funcio que s'encarrega de printar la playlist mes nova i la mes vella que tenim a faborits
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    private void playlistInfo(ArrayDto json) {

        String nova = new String();
        String vella = new String();
        ArrayVideo arrayplay = new ArrayVideo();


        Date novadate = new Date(0, 1, 1);                  //Creem dates molt futures o anteriors com a punt de referencia per la comparacio
        Date velladate = new Date(999999, 31, 12);


        arrayplay = buscaInfo(json, 3, 0);                      //creem array de les playlists del jsonfavourites

        if (arrayplay.getItems().size() != 0) {

            for (int i = 0; i < arrayplay.getItems().size(); i++) {              //iterem sobre totes les playlist

                if ((novadate.compareTo(arrayplay.getItems().get(i).getSnippet().getPublishedAt()) < 0)) {          //guardem si es més nova que la anterior guardada
                    novadate = arrayplay.getItems().get(i).getSnippet().getPublishedAt();
                    nova = arrayplay.getItems().get(i).getSnippet().getTitle();

                }

                if ((velladate.compareTo(arrayplay.getItems().get(i).getSnippet().getPublishedAt()) > 0)) {         //guardem si es més vella que la anterior guardada
                    velladate = arrayplay.getItems().get(i).getSnippet().getPublishedAt();
                    vella = arrayplay.getItems().get(i).getSnippet().getTitle();
                }

            }

            if (arrayplay.getItems().size() == 1) {
                System.out.println("Nova i vella: " + nova);
            } else {
                System.out.println("Nova: " + nova);
                System.out.println("Vella: " + vella);
            }


        }

    }

}

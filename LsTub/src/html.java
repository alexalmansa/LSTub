import java.io.FileWriter;

import static java.lang.Boolean.FALSE;

/**
 * Classe que gestiona la informacio del video
 *
 * @author Alex Almansa, Marc Llort
 */
public class html {

    /**
     * S'encarrega de generar l'arxiu html de les playlists que hi ha a favoritePlaces.json
     *
     * @param json: arxiu on hi ha tota la informacio dels videos,  playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    public static void playlistHtml(ArrayDto json) {
       try {

        Video v = new Video();
        ArrayVideo arrayplay = new ArrayVideo();
        arrayplay = v.buscaInfo(json, 3, 0);            //El tipus 3 ens retorna la arrayvideo amb infromacio de la playlist
        try {

            for (int i = 0; i < arrayplay.getItems().size(); i++) {      //Iterem sobre el total de playlist que tenim
                String codi = new String();
                StringBuilder htmlBuilder = new StringBuilder();


                htmlBuilder.append("<p>\n");

                htmlBuilder.append(retornaPlay(json, i));          //Funcio que ens retorna els videos classificats en playlists
                htmlBuilder.append("</p>\n");

                codi = htmlBuilder.toString();                  //pasem a string el codi

                htmlGenerator(codi, arrayplay.getItems().get(i).getSnippet().getTitle());     //Creem html de playlist
            }

        } catch (Exception e) {
            System.out.println("No hi ha cap playlist guardada a favorits!");
        }
       } catch (Exception buit) {
           System.out.println("Fitxer buit!");
       }
    }




    /**
     * Funcio que s'encarrega de generar un mosaic amb tots els videos, channels i playlists que tenim a favorits
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @author Alex Almansa, Marc Llort
     */
    public static void mosaic(ArrayDto json) {
        try {
            String codi = new String();
            StringBuilder htmlBuilder = new StringBuilder();
            Boolean acabat = FALSE;

            htmlBuilder.append("<table>\n");

            int fila = 0;

            while (!acabat) {                                                 //Fins que no haguem colocat cada Element, no parem d'inserir

                htmlBuilder.append("<tr>\n");                               //Obrim fila

                for (int i = 0 + fila; (i < 4 + fila) && !acabat; i++) {      //Fem que per fila hi hagi 4 elements, quan els superem salta a una nova fila

                    htmlBuilder.append("<td>\n");                           //Obrim element

                    htmlBuilder.append(retornaHTML(json, i));               //Ens dona el codi que cal inserir, tenint en compte si son videos, canals o playlists
                    acabat = complet(json, i + 1);                         //Comprovem si cal afegir un element, o acabem d'inserir-ne perque ja els hem fet tots
                    htmlBuilder.append("</td>\n");                          //Tanquem element

                }
                htmlBuilder.append("</tr>\n");                              //Tanquem fila
                fila = fila + 4;
            }

            htmlBuilder.append("</table>\n");
            codi = htmlBuilder.toString();                                  //Pasem a string el codi contruit
            htmlGenerator(codi, "MOSAIC");                        //Generem el html del mosaic
        } catch (Exception out) {
            System.out.println("Error! Json buit!");
        }
    }


    /**
     * Funcio que s'encarrega de generar un string amb tota la informacio de les playlists
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @return htmlBuilder String on hi ha tot el html
     * @author Alex Almansa, Marc Llort
     */
    private static String retornaPlay(ArrayDto json, int i) {

        StringBuilder htmlBuilder = new StringBuilder();
        ArrayVideo arrayplay = new ArrayVideo();
        ArrayVideo arrayplay2 = new ArrayVideo();
        Video v = new Video();

        arrayplay = v.buscaInfo(json, 3, 0);            //El tipus 3 ens retorna la arrayvideo amb infromacio de la playlist


        arrayplay2 = v.buscaInfo(json, 4, i);               //El tipus 4 retorna la informacio dels videos de la playlist

        htmlBuilder.append("<h1>" + arrayplay.getItems().get(i).getSnippet().getTitle() + ": " + arrayplay2.getPageInfo().getTotalResults() + " videos</h1>\n");

        for (int j = 0; j < arrayplay2.getItems().size(); j++) {      //Iterem sobre total videos de la playlist

            htmlBuilder.append("<h4>" + arrayplay2.getItems().get(j).getSnippet().getTitle() + "</h4>");
            htmlBuilder.append("<a href=https://www.youtube.com/watch?v=" + arrayplay2.getItems().get(j).getSnippet().getResourceId().getVideoId() + ">");

            if (arrayplay2.getItems().get(j).getSnippet().getThumbnails().getStandard() != null) {                                                          //Controlem que si no pot agafar la qualitat standart pugui agafarne una altra
                htmlBuilder.append("<img src=" + arrayplay2.getItems().get(j).getSnippet().getThumbnails().getStandard().getUrl() + "                alt=\"Error al carregar imatge\"/>");
            } else {
                htmlBuilder.append("<img src=" + arrayplay2.getItems().get(j).getSnippet().getThumbnails().getHigh().getUrl() + "                alt=\"Error al carregar imatge\"/>");
            }

            htmlBuilder.append("</a>");
        }


        return htmlBuilder.toString();
    }


    /**
     * Funcio que s'encarrega de generar el html rebent un String
     *
     * @param codi:      string on hi ha tota la informacio que s'afegira al html
     * @param nomfitxer: nom amb el que es guardara el fitxer
     * @author Alex Almansa, Marc Llort
     */
    private static void htmlGenerator(String codi, String nomfitxer) {
        try {

            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<!doctype html>\n");
            htmlBuilder.append("<html lang=\"es\">\n");
            htmlBuilder.append("<head>\n    <meta charset=\"UTF-8\">\n<title>Fitxer bàsic</title>\n<meta name=\"author\" content=\"Marc Llort-marc.llort.2016    Alex Almansa-alex.almansa.2016\">");
            htmlBuilder.append("</head>\n");
            htmlBuilder.append("<body>\n");
            htmlBuilder.append(codi);
            htmlBuilder.append("</body>\n");
            htmlBuilder.append("</html>");
            String html = htmlBuilder.toString();
            FileWriter fileWriter = new FileWriter(nomfitxer + ".html");
            fileWriter.write(html);
            fileWriter.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Funcio que s'encarrega de generar un string amb la informacio de  un video, canal o playlist en format html
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @param i:    contador que ens indica quina es la posicio en que es troba el element que volem afegir al string
     * @return html:  String on hi ha la informacio del element tractat en la funcio
     * @author Alex Almansa, Marc Llort
     */
    private static String retornaHTML(ArrayDto json, int i) {
        String html = new String();
        //A cada if mirem de quin tipus es el element, i segons quin és, afegim el seu id a una url o una altra
        if (json.getItems().get(i).getId().getVideoId() != null) {
            html = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/" + json.getItems().get(i).getId().getVideoId() + "\"></iframe>\n";
        }
        if (json.getItems().get(i).getId().getChannelId() != null) {
            html = canalEmbed(json, i);                                     //En el cas del canal ens cal afegir que sigui clickable i comprovar tamanys, cridem funcio
        }
        if (json.getItems().get(i).getId().getPlaylistId() != null) {
            html = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/videoseries?list=" + json.getItems().get(i).getId().getPlaylistId() + "\"></iframe>\n";

        }

        return html;
    }


    /**
     * Funcio que s'encarrega de generar el string perque surti la imatge amb el link d'un canal al mosaic
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @param i:    contador que ens indica en quina posicio del json es troba el canal del que volem extreure la informacio
     * @return codi: String on hi ha la infomacio del canal en format html
     * @author Alex Almansa, Marc Llort
     */
    private static String canalEmbed(ArrayDto json, int i) {

        String codi = new String();
        StringBuilder htmlBuilder = new StringBuilder();
        String id = json.getItems().get(i).getId().getChannelId();

        htmlBuilder.append("<a href=https://www.youtube.com/channel/" + id + ">");          // Creem enllaç al canal

        if (json.getItems().get(i).getSnippet().getThumbnails().getStandard() != null) {                                                                                                 //Comprovem que agafin diferents mides en cas d'error
            htmlBuilder.append("<img src=" + json.getItems().get(i).getSnippet().getThumbnails().getStandard().getUrl() + " width=\"640\" height=\"360\"               alt=\"Error al carregar imatge\"/>");
        } else {
            htmlBuilder.append("<img src=" + json.getItems().get(i).getSnippet().getThumbnails().getHigh().getUrl() + " width=\"640\" height=\"360\"               alt=\"Error al carregar imatge\"/>");
        }

        htmlBuilder.append("</a>");
        codi = htmlBuilder.toString();

        return codi;

    }


    /**
     * Funcio que s'encarrega de comprovar quan arribem al final de la llista de faborits
     *
     * @param json: arxiu on hi ha la informacio dels videos, playlists i chanels favorits
     * @param i:    contador que ens mostra en quina posicio del arxiu ens trobem
     * @return boolean: retorna true si esta al final de la llista i fals si no ho esta
     * @author Alex Almansa, Marc Llort
     */
    private static Boolean complet(ArrayDto json, int i) {       //Ens retorna boolea true en cas de que no trobi cap element a la casella indicada
        try {
            if (json.getItems().get(i) == null) {
                return Boolean.TRUE;
            } else {
                return FALSE;
            }
        } catch (IndexOutOfBoundsException buit) {

            return FALSE;
        }

    }


}

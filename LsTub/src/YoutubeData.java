import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 Classe que tracta tota la informacio del json
 @author	Alex Almansa, Marc Llort

 */

public  class YoutubeData extends Json{




    /**
     Funcio que s'encarrega de que en cas que calgui, es passi de pagina i si es vol guardar un video, actualitzar el json
     @author	Alex Almansa, Marc Llort
     @param cerca: string on hi ha la cerca que l'usuari vol realitzar
     @param resultats: integer que decideix el nombre de resultats a mostrar
     @param pagina: indica al creajsonobject si ha de cambiar de pagina

     */
    public void printaOpcions(String cerca, int resultats, int pagina)  {
        Boolean error;
        ArrayDto dto = new ArrayDto();
        dto = creaJsonObject(cerca, pagina);
        printador(resultats, dto);

        if(dto.getItems().size() == 0){  error = TRUE;}
        else{ error = FALSE;}

        pagina = llegeixOpcio(error);

        if (pagina != -3 && pagina < 1) {       // Diferent de -3 perque -3 es stop i -2 i -1 son pagina seguent i anterior
            printaOpcions(cerca, resultats, pagina);        //tornem a cridar la mateixa opcio ja a la pagina nova
        }
        else {
            if (pagina != -3) {                 //Casos on volem guardar un video
                jsonCreator(pagina, dto);
            }
        }

    }



    /**
     Funcio que s'encarrega de printar els resultats de la cerca
     @author	Alex Almansa, Marc Llort
     @param resultats: nombre de resultats que es mostren per pagina
     @param dto: array on hi ha la informacio de la cerca , la que es vol printar

     */

    public void printador(int resultats, ArrayDto dto) {
        if(dto.getItems().size() != 0){
            if(resultats > dto.getItems().size()){resultats = dto.getItems().size();}
            for (int i = 0; i < (resultats); i++) {
                int a = i + 1;
                System.out.println("Resultat " + a + ":");
                if (dto.getItems().get(i).getId().getChannelId()!= null) {
                    System.out.println("    Tipus Cerca: Canal");
                }
                if (dto.getItems().get(i).getId().getVideoId() != null) {
                    System.out.println("    Tipus Cerca: Video");
                }
                if (dto.getItems().get(i).getId().getPlaylistId()!= null) {
                    System.out.println("    Tipus Cerca: Playlist");
                }

                System.out.println("    Títol: " + dto.getItems().get(i).getSnippet().getTitle());
                System.out.println("    Descripció: " + dto.getItems().get(i).getSnippet().getDescription());
                System.out.println("    Canal: " + dto.getItems().get(i).getSnippet().getChannelTitle());
                System.out.println();
                System.out.println();
            }
        }
        else{
            System.out.println("No hi ha resultats");
        }

    }












    /**
     Funcio que s'encarrega de cridar les funcions de crear el json object i després de printar els resultats
     @author	Alex Almansa, Marc Llort
     @param cerca: string on hi ha la cerca que es vol realitzar
     @param resultats: el nombre de resultats qeue es mostraran per pagina

     */
    public void printaResultats(String cerca, int resultats)  {

        int opcio;

        ArrayDto dto = creaJsonObject(cerca, 0);
        printador(resultats, dto);

        if (resultats != 3) {           // aixo ho fem perque si estem al cas 1, on es mostren 3 resultats per pagina, no mostri les opcions de seguent pagina i pagina anterior
            opcio = llegeixOpcio(TRUE);
        }
    }

    /**
     Funcio que s'encarrega de classificar els elements de json en un array de  en videos, canals o playlists
     @author	Alex Almansa, Marc Llort
     @param json: array on hi ha tots els videos, canals i playlists
     @param tipus: int que decideix en cas que sigui 1 tornara array ed videos, en cas que sigui 2 de canals , en cas que sigui 3 de la info de les  playlist i 4 la info dels videos de la playlist
     @param html0: en cas de que  busquem informacio d'un video que es troba a una playlis, la posicio del video dins la playlist
     @return arrayinfo: array on hi ha només la informacio del tipus seleccionat

     */
    protected ArrayVideo buscaInfo(ArrayDto json, int tipus, int html0) {

        String urrl = new String();

        ArrayVideo arrayinfo = new ArrayVideo();


        String id = tipusBusq(json, tipus, html0);

        switch (tipus){

            case 1:
                urrl = "https://www.googleapis.com/youtube/v3/videos?key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc&part=statistics&id="+ id;
                break;
            case 2:
                urrl = "https://www.googleapis.com/youtube/v3/channels?key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc&part=statistics&id="+ id;
                break;
            case 3:
                urrl = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc&id="+ id;
                break;
            case 4:
                urrl= "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc&playlistId="+id;
        }
        try {
            URL url = new URL(urrl);

            InputStreamReader reader = new InputStreamReader(url.openStream());

            arrayinfo = new Gson().fromJson(reader, ArrayVideo.class);

        }catch(Exception e){
            System.out.println("Error al conectar a internet");
        }


        return arrayinfo ;
    }


    /**
     Indica el tipus de informacio que conté el item seleccionat
     @author	Alex Almansa, Marc Llort
     @param json: array on hi ha tots els videos, canals i playlists
     @param O: el tipus del que es vol fer la cerca que sera 1 si es video, 2 si es channel, 3 retorna la informacio de la playlist i 4 retorna la informacio dels videos que hi ha dins la playlist
     @param html0: en cas de que  busquem informacio d'un video que es troba a una playlis, la posicio del video dins la playlist
     @return htmlBuilder String on hi ha tot el html

     */
    private String tipusBusq(ArrayDto json, int O, int html0){

        String url = new String();
        String vid = new String();
        String ch = new String();
        String pl = new String();


        if (json.getItems().get(0).getId().getVideoId() != null) {
            vid = vid + json.getItems().get(0).getId().getVideoId()+",";
        }
        if (json.getItems().get(0).getId().getChannelId() != null) {
            ch = ch + json.getItems().get(0).getId().getChannelId()+",";
        }
        if (json.getItems().get(0).getId().getPlaylistId() != null) {
            pl = pl + json.getItems().get(0).getId().getPlaylistId()+",";
        }

        for (int i = 1; i < json.getItems().size()-1; i++) {

            if (json.getItems().get(i).getId().getVideoId() != null) {
                vid = vid + json.getItems().get(i).getId().getVideoId()+",";
            }
            if (json.getItems().get(i).getId().getChannelId() != null) {
                ch = ch + json.getItems().get(i).getId().getChannelId()+",";
            }
            if (json.getItems().get(i).getId().getPlaylistId() != null) {
                pl = pl + json.getItems().get(i).getId().getPlaylistId()+",";
            }
        }

        switch (O){
            case 1:
                url = vid;
                break;
            case 2:
                url = ch;
                break;
            case 3:
                url = pl;
                break;
            case 4:
                String[] playlist = pl.split(",");
                url = playlist[html0];
        }


        return url;
    }


}
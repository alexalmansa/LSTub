import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;

/**
 * Classe que gestiona tota la informacio relacionada amb els videos
 *
 * @author Alex Almansa, Marc Llort
 */
public class Video extends YoutubeData {


    /**
     * Funcio que s'encarrega de printar els videos faborits ordenats per la relacio de likes/dislikes
     *
     * @param json: array on hi ha tots els videos, canals i playlists
     * @author Alex Almansa, Marc Llort
     */
    public void printaFavorits(ArrayDto json) {
        try {
            ArrayVideo arrayvideos = new ArrayVideo();


            arrayvideos = buscaInfo(json, 1, 0);        // Ens quedem de tots els elements, nomes amb els videos

            ArrayVideo ordenat = OrdenaVideos(arrayvideos);            // els ordenem

            ordenat = InsereixTitol(ordenat, json);                     // Inserim el titol

            for (int i = 0; i < arrayvideos.getItems().size(); i++) {
                System.out.println(ordenat.getItems().get(i).getStatistics().getTitle());
                System.out.println(String.format("%.2f", ordenat.getItems().get(i).getStatistics().getRelacio()));
            }
        } catch (IndexOutOfBoundsException buit) {
            System.out.println("Fitxer buit!");
        }
    }


    /**
     * Funcio que s'encarrega de ordenar els videos en funcio de la relacio likes/dislikes
     *
     * @param videos: array on es troben tots els videos a ordenar
     * @return videos: array de videos ordenat
     * @author Alex Almansa, Marc Llort
     */

    private static ArrayVideo OrdenaVideos(ArrayVideo videos) {
        int likes = 0;
        int dislikes = 0;
        String title = new String();
        String id = new String();

        //metode d'ordenacio
        for (int i = 1; i < (videos.getItems().size()); i++) {

            for (int j = 0; j < (videos.getItems().size()); j++) {

                if (videos.getItems().get(i).getStatistics().getRelacio() > videos.getItems().get(j).getStatistics().getRelacio()) {
                    //intercambi de valors

                    title = videos.getItems().get(i).getStatistics().getTitle();
                    likes = videos.getItems().get(i).getStatistics().getLikeCount();
                    dislikes = videos.getItems().get(i).getStatistics().getDislikeCount();
                    id = videos.getItems().get(i).getId();

                    videos.getItems().get(i).getStatistics().setTitle(videos.getItems().get(j).getStatistics().getTitle());
                    videos.getItems().get(i).getStatistics().setLikeCount(videos.getItems().get(j).getStatistics().getLikeCount());
                    videos.getItems().get(i).getStatistics().setDislikeCount(videos.getItems().get(j).getStatistics().getDislikeCount());
                    videos.getItems().get(i).setId(videos.getItems().get(j).getId());

                    videos.getItems().get(j).getStatistics().setLikeCount(likes);
                    videos.getItems().get(j).getStatistics().setDislikeCount(dislikes);
                    videos.getItems().get(j).getStatistics().setTitle(title);
                    videos.getItems().get(j).setId(id);
                }
            }
        }


        return videos;
    }


    /**
     * Funcio que s'encarrega de inserir el titol dels videos
     *
     * @param videos: array on es troben els videos
     * @param json:   array on hi ha tota la informacio i d'on extreiem el titol de cada video
     * @return videos: array de videos amb el titol afegit
     * @author Alex Almansa, Marc Llort
     */
    private ArrayVideo InsereixTitol(ArrayVideo videos, ArrayDto json) {

        for (int m = 0; m < videos.getItems().size(); m++) {
            for (int n = 0; n < json.getItems().size() - 1; n++) {
                if (videos.getItems().get(m).getId().equals(json.getItems().get(n).getId().getVideoId())) {     //Si la id del video es igual a la que hi ha al json copiem el titol

                    videos.getItems().get(m).getStatistics().setTitle(json.getItems().get(n).getSnippet().getTitle());

                }
            }
        }


        return videos;
    }


}
package hu.zsoltborza.gymfinder.util.filehandling;

import com.google.gson.Gson;
import hu.zsoltborza.gymfinder.util.filehandling.model.GymData;
import hu.zsoltborza.gymfinder.util.filehandling.model.GymListItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class FileHandlerUtil {



    public static List<GymListItem> gettingData(){

        StringBuilder builder = new StringBuilder();
        // Reader reader = new InputStreamReader(GsonUtil.class.getResourceAsStream("/json/product.json")))
        //File file = new ClassPathResource("countries.xml").getFile();
        File initialFile = new File("src/main/resources/gym_list.json");
        InputStream is = null;
        try {
            is = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //InputStream is = res.openRawResource(R.raw.gym_hun_new);
        Scanner scanner = new Scanner(is);

        while (scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }

        String file = (builder.toString());

        Gson gson = new Gson();

        GymData gymList = gson.fromJson(file,GymData.class);

        return gymList.getGymList();
    }
}

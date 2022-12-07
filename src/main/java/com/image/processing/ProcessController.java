package com.image.processing;

import com.image.processing.filters.Filter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

public class ProcessController implements Initializable {
    @FXML
    public Menu menuPoint;
    @FXML
    public Menu menuLocal;
    @FXML
    public Menu menuMorphology;
    @FXML
    public Menu menuGlobal;
    @FXML
    public Menu menuGeometry;
    @FXML
    private MenuItem menuUndo;
    private Stage stage;
    private Image image;
    @FXML
    private MenuItem menuOpen;
    @FXML
    private MenuItem menuSave;
    @FXML
    private ImageView imageView;
    public ProcessController(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            File file = fileChooser.showOpenDialog(stage);
            try {
                image = new Image(new FileInputStream(file), imageView.getFitWidth(), imageView.getFitHeight(), false, false);
                imageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        ArrayList<Menu> menusFilters = new ArrayList<>(List.of(menuPoint , menuLocal , menuMorphology,menuGlobal,menuGeometry));
        menusFilters.forEach(menuFilters->
                {
                    String filterType = menuFilters.getId().substring(4).toLowerCase(Locale.ROOT);
                    menuFilters.getItems().forEach(
                            menuItem -> {
                               // System.out.println(menuItem.getId());
                                String filterName = menuItem.getId().substring(4);
                                try {
                                    Class<?> filterClass = Class.forName(Filter.getFullFilterClassName(filterName, filterType));
                                    menuItem.setOnAction(eventFilter -> {
                                        try {
                                            actionFilter(filterClass, imageView.getImage());
                                        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException | InstantiationException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );
        menuSave.setOnAction(event -> {
            File outputFile = new File("result.jpg");
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            try {
                ImageIO.write(bufferedImage, "jpg", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        menuUndo.setOnAction(event -> imageView.setImage(image));
    }

    public void actionFilter(Class<?> filterClass ,Image image) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Method method = Filter.getProcessFilterMethod(filterClass);
        Filter filter = Filter.getFilterByName(filterClass);
        Image result = (Image) method.invoke(filter , image);
        imageView.setImage(result);

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

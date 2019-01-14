package sample;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Data {
    private Object[] dataList = new Object[100];
    private int x;
    private int numObjects;
    private int arrayCounter;
    private boolean queue;
    private ListView listView;
    private ObservableList<String> items = FXCollections.observableArrayList ();

    Data(boolean q, int n, ListView l) {
        numObjects = n;
        queue = q;
        listView = l;

        arrayCounter = -1;
        if (queue) {
            x = -1;
        } else {
            x = numObjects;
        }
    }

    void put(Object data) {
        arrayCounter++;
        dataList[arrayCounter] = data;
    }

    synchronized boolean get() {
        if (x + 1 > arrayCounter) {
            //System.out.println("ERROR: Data cannot output more than index of " + arrayCounter + ".");
            return false;
        } else if (x + 1 < 0) {
            //System.out.println("ERROR: Data cannot output less than index of 0.");
            return false;
        } else if (x + 1 > dataList.length) {
            //System.out.println("ERROR: Data cannot output more than index of size.");
            return false;
        }

        try {
            if (queue) {
                x++;
            } else {
                x--;
            }

            Platform.runLater(() -> {
                listView.getItems().add(dataList[x]);

                /*listView.setItems(items);
                listView.setCellFactory(listView -> new ListCell<String>() {
                    private ImageView imageView = new ImageView();
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            if (dataList[x].getClass().getName().equals("java.lang.String")) {
                                setText((String) dataList[x]);
                            } else if (dataList[x].getClass().getName().equals("javafx.scene.image.Image")) {
                                Image image = (Image) dataList[x];
                                imageView.setImage(image);
                                setGraphic(imageView);
                            }
                        }
                    }*/
                });

                //System.out.println(dataList[x].getClass().getName());
                /*if (dataList[x].getClass().getName().equals("java.lang.String")) {
                    listView.getItems().add(dataList[x]);
                } else if (dataList[x].getClass().getName().equals("javafx.scene.image.Image")) {

                }*/
            System.out.println("Order Stack " + x + ": " + dataList[x]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }

        return true;
    }
}

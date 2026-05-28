package com.example.cargoprogram;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class HelloApplication extends Application {

    private static final double GAME_WIDTH = 1440;
    private static final double GAME_HEIGHT = 810;
    private static final double WINDOW_WIDTH = 1100;
    private static final double WINDOW_HEIGHT = 619;

    private static final double SHIP_WIDTH = 300;
    private static final double CONTAINER_STACK_WIDTH = 180;
    private static final double SINGLE_CONTAINER_WIDTH = 55;

    private final Random random = new Random();

    private boolean shipLoaded = false;
    private boolean shipSailed = false;
    private boolean podUnloaded = false;

    @Override
    public void start(Stage stage) {

        Pane gamePane = new Pane();

        Image mapImage = new Image(
                getClass().getResource("/images/map.png").toExternalForm()
        );

        Image podMapImage = new Image(
                getClass().getResource("/images/pod_map.png").toExternalForm()
        );

        ImageView mapView = new ImageView(mapImage);
        mapView.setFitWidth(GAME_WIDTH);
        mapView.setFitHeight(GAME_HEIGHT);
        mapView.setPreserveRatio(false);
        gamePane.getChildren().add(mapView);

        Image emptyShipImage = new Image(
                getClass().getResource("/images/ship.png").toExternalForm()
        );

        Image emptyShipReverseImage = new Image(
                getClass().getResource("/images/ship_reverse.png").toExternalForm()
        );

        Image loadedShipImage = new Image(
                getClass().getResource("/images/load_ship.png").toExternalForm()
        );

        Image loadedShipFrontImage = new Image(
                getClass().getResource("/images/load_ship_front.png").toExternalForm()
        );

        Image loadedShipReverseImage = new Image(
                getClass().getResource("/images/load_ship_reverse.png").toExternalForm()
        );

        Image loadedShipSideImage = new Image(
                getClass().getResource("/images/load_ship_side.png").toExternalForm()
        );

        Image containerStackImage = new Image(
                getClass().getResource("/images/container.png").toExternalForm()
        );

        Image[] singleContainers = {
                new Image(getClass().getResource("/images/red_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/orange_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/blue_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/green_container.png").toExternalForm())
        };

        ImageView ship = new ImageView(emptyShipImage);
        ship.setFitWidth(SHIP_WIDTH);
        ship.setPreserveRatio(true);
        ship.setLayoutX(110);
        ship.setLayoutY(315);
        ship.setRotate(2);
        ship.setCursor(Cursor.HAND);
        gamePane.getChildren().add(ship);

        ImageView containerStack = new ImageView(containerStackImage);
        containerStack.setFitWidth(CONTAINER_STACK_WIDTH);
        containerStack.setPreserveRatio(true);
        containerStack.setLayoutX(1010);
        containerStack.setLayoutY(360);
        containerStack.setRotate(-1);
        containerStack.setCursor(Cursor.HAND);
        gamePane.getChildren().add(containerStack);

        containerStack.setOnMouseClicked(e -> {
            containerStack.setDisable(true);

            for (int i = 0; i < 4; i++) {
                int index = i;

                PauseTransition delay =
                        new PauseTransition(Duration.seconds(index * 0.25));

                delay.setOnFinished(event -> {
                    Image randomContainer =
                            singleContainers[random.nextInt(singleContainers.length)];

                    ImageView movingContainer =
                            new ImageView(randomContainer);

                    movingContainer.setFitWidth(SINGLE_CONTAINER_WIDTH);
                    movingContainer.setPreserveRatio(true);
                    movingContainer.setLayoutX(1030);
                    movingContainer.setLayoutY(380);
                    movingContainer.setRotate(2);

                    gamePane.getChildren().add(movingContainer);
                    movingContainer.toFront();

                    double targetX = 185 + (index % 2) * 55;
                    double targetY = 335 + (index / 2) * 35;

                    TranslateTransition move =
                            new TranslateTransition(Duration.seconds(1.0), movingContainer);

                    move.setToX(targetX - movingContainer.getLayoutX());
                    move.setToY(targetY - movingContainer.getLayoutY());

                    move.setOnFinished(finish -> {
                        movingContainer.setVisible(false);

                        if (index == 3) {
                            ship.setImage(loadedShipImage);
                            shipLoaded = true;
                        }
                    });

                    move.play();
                });

                delay.play();
            }
        });

        ship.setOnMouseClicked(e -> {

            if (!shipLoaded || shipSailed) {
                return;
            }

            shipSailed = true;

            PauseTransition turnFront =
                    new PauseTransition(Duration.seconds(0.3));

            turnFront.setOnFinished(event -> {
                ship.setImage(loadedShipFrontImage);
            });

            PauseTransition turnReverse =
                    new PauseTransition(Duration.seconds(0.8));

            turnReverse.setOnFinished(event -> {
                ship.setImage(loadedShipReverseImage);
            });

            PauseTransition turnSide =
                    new PauseTransition(Duration.seconds(1.3));

            turnSide.setOnFinished(event -> {
                ship.setImage(loadedShipSideImage);
            });

            TranslateTransition sailMove =
                    new TranslateTransition(Duration.seconds(2.5), ship);

            sailMove.setByX(-300);
            sailMove.setByY(0);

            sailMove.setOnFinished(event -> {

                mapView.setImage(podMapImage);

                containerStack.setVisible(false);

                ImageView podContainerStack = new ImageView(containerStackImage);
                podContainerStack.setFitWidth(CONTAINER_STACK_WIDTH);
                podContainerStack.setPreserveRatio(true);
                podContainerStack.setLayoutX(250);
                podContainerStack.setLayoutY(370);
                podContainerStack.setRotate(-1);
                gamePane.getChildren().add(podContainerStack);

                ship.setImage(loadedShipSideImage);
                ship.setLayoutX(1160);
                ship.setLayoutY(430);
                ship.setRotate(0);
                ship.setTranslateX(0);
                ship.setTranslateY(0);
                ship.toFront();

                TranslateTransition enterPod =
                        new TranslateTransition(Duration.seconds(2.5), ship);

                enterPod.setByX(-260);
                enterPod.setByY(0);

                enterPod.setOnFinished(podFinish -> {

                    ship.setImage(loadedShipReverseImage);
                    ship.setLayoutX(850);
                    ship.setLayoutY(430);
                    ship.setTranslateX(0);
                    ship.setTranslateY(0);
                    ship.toFront();

                    ship.setOnMouseClicked(unloadEvent -> {

                        if (podUnloaded) {
                            return;
                        }

                        podUnloaded = true;

                        for (int i = 0; i < 4; i++) {
                            int index = i;

                            PauseTransition unloadDelay =
                                    new PauseTransition(Duration.seconds(index * 0.25));

                            unloadDelay.setOnFinished(delayEvent -> {

                                Image randomContainer =
                                        singleContainers[random.nextInt(singleContainers.length)];

                                ImageView unloadingContainer =
                                        new ImageView(randomContainer);

                                unloadingContainer.setFitWidth(SINGLE_CONTAINER_WIDTH);
                                unloadingContainer.setPreserveRatio(true);

                                unloadingContainer.setLayoutX(870);
                                unloadingContainer.setLayoutY(450);
                                unloadingContainer.setRotate(-1);

                                gamePane.getChildren().add(unloadingContainer);
                                unloadingContainer.toFront();

                                double targetX = 260 + (index % 2) * 55;
                                double targetY = 390 + (index / 2) * 35;

                                TranslateTransition unloadMove =
                                        new TranslateTransition(Duration.seconds(1.0), unloadingContainer);

                                unloadMove.setToX(targetX - unloadingContainer.getLayoutX());
                                unloadMove.setToY(targetY - unloadingContainer.getLayoutY());

                                unloadMove.setOnFinished(unloadFinish -> {

                                    unloadingContainer.setVisible(false);

                                    if (index == 3) {
                                        ship.setImage(emptyShipReverseImage);

                                        ship.setLayoutX(850);
                                        ship.setLayoutY(430);
                                        ship.setRotate(0);

                                        ship.setTranslateX(0);
                                        ship.setTranslateY(0);

                                        ship.toFront();
                                    }
                                });

                                unloadMove.play();
                            });

                            unloadDelay.play();
                        }
                    });
                });

                enterPod.play();
            });

            turnFront.play();
            turnReverse.play();
            turnSide.play();
            sailMove.play();
        });

        Group gameGroup = new Group(gamePane);

        Pane root = new Pane(gameGroup);
        root.setStyle("-fx-background-color: #25bcea;");

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        NumberBinding gameScale = Bindings.max(
                scene.widthProperty().divide(GAME_WIDTH),
                scene.heightProperty().divide(GAME_HEIGHT)
        );

        Scale scale = new Scale();
        scale.setPivotX(0);
        scale.setPivotY(0);
        scale.xProperty().bind(gameScale);
        scale.yProperty().bind(gameScale);
        gameGroup.getTransforms().add(scale);

        Rectangle sceneClip = new Rectangle();
        sceneClip.widthProperty().bind(scene.widthProperty());
        sceneClip.heightProperty().bind(scene.heightProperty());
        root.setClip(sceneClip);

        stage.setTitle("Cargo Port Simulator");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(80);
        stage.setY(80);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
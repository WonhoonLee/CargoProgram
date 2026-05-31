package com.example.cargoprogram;

import javafx.animation.ParallelTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
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

        Image mapImage = new Image(getClass().getResource("/images/map.png").toExternalForm());
        Image podMapImage = new Image(getClass().getResource("/images/pod_map.png").toExternalForm());

        ImageView mapView = new ImageView(mapImage);
        mapView.setFitWidth(GAME_WIDTH);
        mapView.setFitHeight(GAME_HEIGHT);
        mapView.setPreserveRatio(false);
        gamePane.getChildren().add(mapView);

        Image emptyShipImage = new Image(getClass().getResource("/images/ship.png").toExternalForm());
        Image emptyShipReverseImage = new Image(getClass().getResource("/images/ship_reverse.png").toExternalForm());
        Image loadedShipImage = new Image(getClass().getResource("/images/load_ship.png").toExternalForm());
        Image loadedShipFrontImage = new Image(getClass().getResource("/images/load_ship_front.png").toExternalForm());
        Image loadedShipReverseImage = new Image(getClass().getResource("/images/load_ship_reverse.png").toExternalForm());
        Image loadedShipSideImage = new Image(getClass().getResource("/images/load_ship_side.png").toExternalForm());

        Image containerStackImage = new Image(getClass().getResource("/images/container.png").toExternalForm());

        Image truckImage = new Image(getClass().getResource("/images/truck_left_upper.png").toExternalForm());
        Image truckHorizontalReverseImage = new Image(getClass().getResource("/images/truck_hr_reverse.png").toExternalForm());
        Image truckRightUpperImage = new Image(getClass().getResource("/images/truck_right_upper.png").toExternalForm());
        Image truckReverseImage = new Image(getClass().getResource("/images/truck_reverse.png").toExternalForm());
        Image truckLoadRightUpperRedImage = new Image(getClass().getResource("/images/truck_load_right_upper_red.png").toExternalForm());
        Image truckRedImage = new Image(getClass().getResource("/images/truck_load_left_upper_red.png").toExternalForm());
        Image truckBlueImage = new Image(getClass().getResource("/images/truck_load_left_upper_blue.png").toExternalForm());
        Image truckGreenImage = new Image(getClass().getResource("/images/truck_load_left_upper_green.png").toExternalForm());
        Image truckOrangeImage = new Image(getClass().getResource("/images/truck_load_left_upper_orange.png").toExternalForm());

        Image truckRedHorizontalImage = new Image(getClass().getResource("/images/truck_load_red_hr.png").toExternalForm());
        Image truckRedDiagonalImage = new Image(getClass().getResource("/images/truck_load_red.png").toExternalForm());
        Image truckRedVerticalImage = new Image(getClass().getResource("/images/truck_load_red_vertical.png").toExternalForm());
        Image truckRedReverseImage = new Image(getClass().getResource("/images/truck_load_red_reverse.png").toExternalForm());

        Image truckBlueHorizontalImage = new Image(getClass().getResource("/images/truck_load_blue_hr.png").toExternalForm());
        Image truckBlueDiagonalImage = new Image(getClass().getResource("/images/truck_load_blue.png").toExternalForm());
        Image truckBlueVerticalImage = new Image(getClass().getResource("/images/truck_load_blue_vertical.png").toExternalForm());
        Image truckBlueReverseImage = new Image(getClass().getResource("/images/truck_load_blue_reverse.png").toExternalForm());

        Image truckGreenHorizontalImage = new Image(getClass().getResource("/images/truck_load_green_hr.png").toExternalForm());
        Image truckGreenDiagonalImage = new Image(getClass().getResource("/images/truck_load_green.png").toExternalForm());
        Image truckGreenVerticalImage = new Image(getClass().getResource("/images/truck_load_green_vertical.png").toExternalForm());
        Image truckGreenReverseImage = new Image(getClass().getResource("/images/truck_load_green_reverse.png").toExternalForm());

        Image truckOrangeHorizontalImage = new Image(getClass().getResource("/images/truck_load_orange_hr.png").toExternalForm());
        Image truckOrangeDiagonalImage = new Image(getClass().getResource("/images/truck_load_orange.png").toExternalForm());
        Image truckOrangeVerticalImage = new Image(getClass().getResource("/images/truck_load_orange_vertical.png").toExternalForm());
        Image truckOrangeReverseImage = new Image(getClass().getResource("/images/truck_load_orange_reverse.png").toExternalForm());

        Image craneBodyImage = new Image(getClass().getResource("/images/crane_body.png").toExternalForm());
        Image craneBeamImage = new Image(getClass().getResource("/images/crane_system_beam.png").toExternalForm());
        Image craneTrolleyImage = new Image(getClass().getResource("/images/crane_trolley.png").toExternalForm());
        Image craneSpreaderImage = new Image(getClass().getResource("/images/crane_spreade.png").toExternalForm());
        Image craneBeamReverseImage = new Image(getClass().getResource("/images/crane_system_beam_reverse.png").toExternalForm());
        Image craneSpreaderReverseImage = new Image(getClass().getResource("/images/crane_spreade_reverse.png").toExternalForm());
        Image craneTrolleyReverseImage = new Image(getClass().getResource("/images/crane_trolley.png").toExternalForm());

        ImageView craneBody = new ImageView(craneBodyImage);
        craneBody.setFitWidth(230);
        craneBody.setPreserveRatio(true);
        craneBody.setLayoutX(225);
        craneBody.setLayoutY(260);

        ImageView craneBeam = new ImageView(craneBeamImage);
        craneBeam.setFitWidth(250);
        craneBeam.setPreserveRatio(true);
        craneBeam.setLayoutX(210);
        craneBeam.setLayoutY(195);

        ImageView craneTrolley = new ImageView(craneTrolleyImage);
        craneTrolley.setFitWidth(45);
        craneTrolley.setPreserveRatio(true);
        craneTrolley.setLayoutX(270);
        craneTrolley.setLayoutY(300);

        ImageView craneSpreader = new ImageView(craneSpreaderImage);
        craneSpreader.setFitWidth(65);
        craneSpreader.setPreserveRatio(true);
        craneSpreader.setLayoutX(260);
        craneSpreader.setLayoutY(315);

        Line craneWire = new Line();
        craneWire.setStroke(Color.DARKGRAY);
        craneWire.setStrokeWidth(3);
        craneWire.setStrokeLineCap(StrokeLineCap.ROUND);

        craneWire.startXProperty().bind(craneTrolley.layoutXProperty().add(craneTrolley.translateXProperty()).add(22));
        craneWire.startYProperty().bind(craneTrolley.layoutYProperty().add(craneTrolley.translateYProperty()).add(38));
        craneWire.endXProperty().bind(craneSpreader.layoutXProperty().add(craneSpreader.translateXProperty()).add(32));
        craneWire.endYProperty().bind(craneSpreader.layoutYProperty().add(craneSpreader.translateYProperty()).add(8));

        gamePane.getChildren().addAll(craneBody, craneTrolley, craneBeam, craneWire, craneSpreader);

        Image[] singleContainers = {
                new Image(getClass().getResource("/images/red_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/orange_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/blue_container.png").toExternalForm()),
                new Image(getClass().getResource("/images/green_container.png").toExternalForm())
        };

        Image[] loadedTruckImages = {
                truckRedImage,
                truckBlueImage,
                truckGreenImage,
                truckOrangeImage
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

        ImageView truck1 = createTruck(truckImage, 1050, 290);
        ImageView truck2 = createTruck(truckImage, 1120, 329);
        ImageView truck3 = createTruck(truckImage, 1190, 368);
        ImageView truck4 = createTruck(truckImage, 1250, 400);

        gamePane.getChildren().addAll(truck1, truck2, truck3, truck4);

        ImageView[] trucks = {truck1, truck2, truck3, truck4};

        final boolean[] truck1Arrived = {false};
        final boolean[] craneUsed = {false};
        final boolean[] podCraneUsed = {false};

        truck1.setOnMouseClicked(e -> moveTruckToCrane(
                truck1,
                truckRedHorizontalImage,
                truckRedDiagonalImage,
                truckRedVerticalImage,
                truckRedReverseImage,
                craneBody, craneBeam, craneTrolley, craneWire, craneSpreader,
                () -> truck1Arrived[0] = true
        ));

        truck2.setOnMouseClicked(e -> moveTruckToCrane(
                truck2,
                truckBlueHorizontalImage,
                truckBlueDiagonalImage,
                truckBlueVerticalImage,
                truckBlueReverseImage,
                craneBody, craneBeam, craneTrolley, craneWire, craneSpreader,
                null
        ));

        truck3.setOnMouseClicked(e -> moveTruckToCrane(
                truck3,
                truckGreenHorizontalImage,
                truckGreenDiagonalImage,
                truckGreenVerticalImage,
                truckGreenReverseImage,
                craneBody, craneBeam, craneTrolley, craneWire, craneSpreader,
                null
        ));

        truck4.setOnMouseClicked(e -> moveTruckToCrane(
                truck4,
                truckOrangeHorizontalImage,
                truckOrangeDiagonalImage,
                truckOrangeVerticalImage,
                truckOrangeReverseImage,
                craneBody, craneBeam, craneTrolley, craneWire, craneSpreader,
                null
        ));

        Runnable craneClickEvent = () -> {
            if (!truck1Arrived[0] || craneUsed[0]) return;

            craneUsed[0] = true;

            startCraneLoadTruck1ToShip(
                    gamePane,
                    truck1,
                    truckReverseImage,
                    truckHorizontalReverseImage,
                    truckRightUpperImage,
                    singleContainers[0],
                    loadedShipImage,
                    ship,
                    craneBody,
                    craneBeam,
                    craneTrolley,
                    craneWire,
                    craneSpreader
            );
        };

        craneBody.setCursor(Cursor.HAND);
        craneBeam.setCursor(Cursor.HAND);
        craneTrolley.setCursor(Cursor.HAND);
        craneSpreader.setCursor(Cursor.HAND);

        craneBody.setOnMouseClicked(e -> craneClickEvent.run());
        craneBeam.setOnMouseClicked(e -> craneClickEvent.run());
        craneTrolley.setOnMouseClicked(e -> craneClickEvent.run());
        craneSpreader.setOnMouseClicked(e -> craneClickEvent.run());

        containerStack.setOnMouseClicked(e -> {
            containerStack.setDisable(true);

            for (int i = 0; i < 4; i++) {
                int index = i;

                PauseTransition delay = new PauseTransition(Duration.seconds(index * 0.25));

                delay.setOnFinished(event -> {
                    Image randomContainer = singleContainers[random.nextInt(singleContainers.length)];

                    ImageView movingContainer = new ImageView(randomContainer);
                    movingContainer.setFitWidth(SINGLE_CONTAINER_WIDTH);
                    movingContainer.setPreserveRatio(true);
                    movingContainer.setLayoutX(1030);
                    movingContainer.setLayoutY(380);
                    movingContainer.setRotate(2);

                    gamePane.getChildren().add(movingContainer);
                    movingContainer.toFront();

                    double targetX = trucks[index].getLayoutX() + 35;
                    double targetY = trucks[index].getLayoutY() + 35;

                    TranslateTransition move = new TranslateTransition(Duration.seconds(1.0), movingContainer);
                    move.setToX(targetX - movingContainer.getLayoutX());
                    move.setToY(targetY - movingContainer.getLayoutY());

                    move.setOnFinished(finish -> {
                        movingContainer.setVisible(false);
                        trucks[index].setImage(loadedTruckImages[index]);
                        trucks[index].setDisable(false);
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

            PauseTransition turnFront = new PauseTransition(Duration.seconds(0.3));
            turnFront.setOnFinished(event -> ship.setImage(loadedShipFrontImage));

            PauseTransition turnReverse = new PauseTransition(Duration.seconds(0.8));
            turnReverse.setOnFinished(event -> ship.setImage(loadedShipReverseImage));

            PauseTransition turnSide = new PauseTransition(Duration.seconds(1.3));
            turnSide.setOnFinished(event -> ship.setImage(loadedShipSideImage));

            TranslateTransition sailMove = new TranslateTransition(Duration.seconds(2.5), ship);
            sailMove.setByX(-500);
            sailMove.setByY(0);

            sailMove.setOnFinished(event -> {

                mapView.setImage(podMapImage);

                craneBeam.setImage(craneBeamReverseImage);
                craneTrolley.setImage(craneTrolleyReverseImage);
                craneSpreader.setImage(craneSpreaderReverseImage);

                containerStack.setVisible(false);
                craneBody.setLayoutX(850);
                craneBody.setLayoutY(330);
                craneBody.setTranslateX(0);
                craneBody.setTranslateY(0);

                craneBeam.setLayoutX(850);
                craneBeam.setLayoutY(270);
                craneBeam.setTranslateX(0);
                craneBeam.setTranslateY(0);

                craneTrolley.setLayoutX(1000);
                craneTrolley.setLayoutY(385);
                craneTrolley.setTranslateX(0);
                craneTrolley.setTranslateY(0);

                craneSpreader.setLayoutX(990);
                craneSpreader.setLayoutY(400);
                craneSpreader.setTranslateX(0);
                craneSpreader.setTranslateY(0);

                craneBody.toFront();
                craneBeam.toFront();
                craneTrolley.toFront();
                craneWire.toFront();
                craneSpreader.toFront();

                truck1.setVisible(false);
                truck2.setVisible(false);
                truck3.setVisible(false);
                truck4.setVisible(false);

                ImageView podContainerStack = new ImageView(containerStackImage);
                podContainerStack.setFitWidth(CONTAINER_STACK_WIDTH);
                podContainerStack.setPreserveRatio(true);
                podContainerStack.setLayoutX(250);
                podContainerStack.setLayoutY(370);
                podContainerStack.setRotate(-1);
                gamePane.getChildren().add(podContainerStack);
                podContainerStack.toFront();

                ship.setImage(loadedShipSideImage);
                ship.setLayoutX(1160);
                ship.setLayoutY(430);
                ship.setRotate(0);
                ship.setTranslateX(0);
                ship.setTranslateY(0);
                ship.toFront();

                TranslateTransition enterPod = new TranslateTransition(Duration.seconds(2.5), ship);
                enterPod.setByX(-260);
                enterPod.setByY(0);

                enterPod.setOnFinished(podFinish -> {

                    ship.setImage(loadedShipReverseImage);
                    ship.setLayoutX(850);
                    ship.setLayoutY(430);
                    ship.setTranslateX(0);
                    ship.setTranslateY(0);
                    ship.toFront();

                    ImageView podTruck = new ImageView(truckRightUpperImage);
                    podTruck.setFitWidth(135);
                    podTruck.setPreserveRatio(true);

                    podTruck.setLayoutX(820);
                    podTruck.setLayoutY(380);

                    gamePane.getChildren().add(podTruck);
                    podTruck.toFront();

                    Runnable podCraneClickEvent = () -> {
                        if (podCraneUsed[0] || podUnloaded) return;

                        podCraneUsed[0] = true;

                        startPodUnloadShipToTruck(
                                gamePane,
                                ship,
                                emptyShipReverseImage,
                                singleContainers[0],
                                podTruck,
                                truckLoadRightUpperRedImage,
                                craneBody,
                                craneBeam,
                                craneTrolley,
                                craneWire,
                                craneSpreader
                        );
                    };

                    craneBody.setOnMouseClicked(click -> podCraneClickEvent.run());
                    craneBeam.setOnMouseClicked(click -> podCraneClickEvent.run());
                    craneTrolley.setOnMouseClicked(click -> podCraneClickEvent.run());
                    craneSpreader.setOnMouseClicked(click -> podCraneClickEvent.run());
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

    private ImageView createTruck(Image truckImage, double x, double y) {
        ImageView truck = new ImageView(truckImage);
        truck.setFitWidth(120);
        truck.setPreserveRatio(true);
        truck.setLayoutX(x);
        truck.setLayoutY(y);
        truck.setDisable(true);
        truck.setCursor(Cursor.HAND);
        return truck;
    }

    private void moveTruckToCrane(
            ImageView truck,
            Image horizontalImage,
            Image diagonalImage,
            Image verticalImage,
            Image reverseImage,
            ImageView craneBody,
            ImageView craneBeam,
            ImageView craneTrolley,
            Line craneWire,
            ImageView craneSpreader,
            Runnable onArrived
    ) {
        truck.setDisable(true);

        TranslateTransition move1 = new TranslateTransition(Duration.seconds(3), truck);
        move1.setToX(580 - truck.getLayoutX());
        move1.setToY(50 - truck.getLayoutY());

        move1.setOnFinished(e -> {
            truck.setImage(horizontalImage);
            truck.setFitWidth(150);
            truck.setPreserveRatio(true);

            PauseTransition turnPause1 = new PauseTransition(Duration.seconds(0.25));

            turnPause1.setOnFinished(p1 -> {
                truck.setImage(diagonalImage);
                truck.setFitWidth(140);
                truck.setPreserveRatio(true);

                TranslateTransition move2 = new TranslateTransition(Duration.seconds(2.0), truck);
                move2.setToX(250 - truck.getLayoutX());
                move2.setToY(230 - truck.getLayoutY());

                move2.setOnFinished(e2 -> {
                    truck.setImage(verticalImage);
                    truck.setFitWidth(80);
                    truck.setPreserveRatio(true);

                    craneBody.toFront();
                    craneBeam.toFront();
                    craneTrolley.toFront();
                    craneWire.toFront();
                    craneSpreader.toFront();

                    PauseTransition turnPause2 = new PauseTransition(Duration.seconds(0.25));

                    turnPause2.setOnFinished(p2 -> {
                        truck.setImage(reverseImage);
                        truck.setFitWidth(135);
                        truck.setPreserveRatio(true);

                        TranslateTransition move3 = new TranslateTransition(Duration.seconds(1.3), truck);
                        move3.setToX(330 - truck.getLayoutX());
                        move3.setToY(300 - truck.getLayoutY());

                        move3.setOnFinished(done -> {
                            if (onArrived != null) onArrived.run();
                        });

                        move3.play();
                    });

                    turnPause2.play();
                });

                move2.play();
            });

            turnPause1.play();
        });

        move1.play();
    }

    private void startCraneLoadTruck1ToShip(
            Pane gamePane,
            ImageView truck,
            Image emptyTruckReverseImage,
            Image emptyTruckHorizontalImage,
            Image emptyTruckRightUpperImage,
            Image containerImage,
            Image loadedShipImage,
            ImageView ship,
            ImageView craneBody,
            ImageView craneBeam,
            ImageView craneTrolley,
            Line craneWire,
            ImageView craneSpreader
    ) {

        craneBody.toFront();
        craneBeam.toFront();
        craneTrolley.toFront();
        craneWire.toFront();
        craneSpreader.toFront();

        double trolleyTruckX = 380;
        double spreaderTruckX = 370;

        double spreaderHighY = 270;
        double spreaderLowY = 315;

        double containerTruckX = 380;
        double containerTruckY = 330;

        double trolleyShipX = 255;
        double spreaderShipX = 255;

        double shipHighY = 320;
        double shipLowY = 370;

        double containerShipX = 255;
        double containerShipMoveY = 325;
        double containerShipY = 380;

        ImageView liftingContainer = new ImageView(containerImage);
        liftingContainer.setFitWidth(SINGLE_CONTAINER_WIDTH);
        liftingContainer.setPreserveRatio(true);
        liftingContainer.setLayoutX(containerTruckX);
        liftingContainer.setLayoutY(containerTruckY);
        liftingContainer.setVisible(false);

        gamePane.getChildren().add(liftingContainer);

        TranslateTransition trolleyToTruck = new TranslateTransition(Duration.seconds(1.0), craneTrolley);
        trolleyToTruck.setToX(trolleyTruckX - craneTrolley.getLayoutX());
        trolleyToTruck.setToY(-50);

        TranslateTransition spreaderXToTruck = new TranslateTransition(Duration.seconds(1.0), craneSpreader);
        spreaderXToTruck.setToX(spreaderTruckX - craneSpreader.getLayoutX());
        spreaderXToTruck.setToY(-50);

        ParallelTransition moveXToTruck = new ParallelTransition(trolleyToTruck, spreaderXToTruck);

        moveXToTruck.setOnFinished(e -> {
            TranslateTransition spreaderDown = new TranslateTransition(Duration.seconds(0.8), craneSpreader);
            spreaderDown.setToY(spreaderLowY - craneSpreader.getLayoutY());

            spreaderDown.setOnFinished(downFinish -> {
                liftingContainer.setVisible(true);
                liftingContainer.toFront();
                craneSpreader.toFront();

                double containerOffsetX =
                        liftingContainer.getLayoutX()
                                - (craneSpreader.getLayoutX() + craneSpreader.getTranslateX());

                double containerOffsetY =
                        liftingContainer.getLayoutY()
                                - (craneSpreader.getLayoutY() + craneSpreader.getTranslateY());

                TranslateTransition spreaderUp = new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                spreaderUp.setToY(spreaderHighY - craneSpreader.getLayoutY());

                TranslateTransition containerUp = new TranslateTransition(Duration.seconds(0.8), liftingContainer);
                containerUp.setToX(
                        craneSpreader.getLayoutX()
                                + craneSpreader.getTranslateX()
                                + containerOffsetX
                                - liftingContainer.getLayoutX()
                );
                containerUp.setToY(spreaderHighY + containerOffsetY - liftingContainer.getLayoutY());

                ParallelTransition liftUp = new ParallelTransition(spreaderUp, containerUp);

                liftUp.setOnFinished(upFinish -> {
                    truck.setImage(emptyTruckReverseImage);
                    truck.setFitWidth(135);
                    truck.setPreserveRatio(true);

                    TranslateTransition emptyMove1 = new TranslateTransition(Duration.seconds(1.2), truck);
                    emptyMove1.setToX(470 - truck.getLayoutX());
                    emptyMove1.setToY(350 - truck.getLayoutY());

                    emptyMove1.setOnFinished(ev -> {
                        truck.setImage(emptyTruckHorizontalImage);
                        truck.setFitWidth(150);
                        truck.setPreserveRatio(true);

                        PauseTransition turnPause = new PauseTransition(Duration.seconds(0.25));

                        turnPause.setOnFinished(turn -> {
                            truck.setImage(emptyTruckRightUpperImage);
                            truck.setFitWidth(135);
                            truck.setPreserveRatio(true);

                            TranslateTransition emptyMove2 = new TranslateTransition(Duration.seconds(2.0), truck);
                            emptyMove2.setToX(730 - truck.getLayoutX());
                            emptyMove2.setToY(190 - truck.getLayoutY());
                            emptyMove2.play();
                        });

                        turnPause.play();
                    });

                    emptyMove1.play();

                    TranslateTransition trolleyToShip = new TranslateTransition(Duration.seconds(1.3), craneTrolley);
                    trolleyToShip.setToX(trolleyShipX - craneTrolley.getLayoutX());
                    trolleyToShip.setToY(0);

                    TranslateTransition spreaderXToShip = new TranslateTransition(Duration.seconds(1.3), craneSpreader);
                    spreaderXToShip.setToX(spreaderShipX - craneSpreader.getLayoutX());
                    spreaderXToShip.setToY(0);

                    TranslateTransition containerXToShip = new TranslateTransition(Duration.seconds(1.3), liftingContainer);
                    containerXToShip.setToX(containerShipX - liftingContainer.getLayoutX());
                    containerXToShip.setToY(containerShipMoveY - liftingContainer.getLayoutY());

                    ParallelTransition moveXToShip =
                            new ParallelTransition(trolleyToShip, spreaderXToShip, containerXToShip);

                    moveXToShip.setOnFinished(shipMoveFinish -> {
                        TranslateTransition spreaderDownToShip =
                                new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                        spreaderDownToShip.setToY(shipLowY - craneSpreader.getLayoutY());

                        TranslateTransition containerDownToShip =
                                new TranslateTransition(Duration.seconds(0.8), liftingContainer);
                        containerDownToShip.setToY(containerShipY - liftingContainer.getLayoutY());

                        ParallelTransition lowerToShip =
                                new ParallelTransition(spreaderDownToShip, containerDownToShip);

                        lowerToShip.setOnFinished(lowerFinish -> {
                            liftingContainer.setVisible(false);
                            ship.setImage(loadedShipImage);
                            shipLoaded = true;

                            TranslateTransition spreaderBackUp =
                                    new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                            spreaderBackUp.setToY(shipHighY - craneSpreader.getLayoutY());
                            spreaderBackUp.play();
                        });

                        lowerToShip.play();
                    });

                    moveXToShip.play();
                });

                liftUp.play();
            });

            spreaderDown.play();
        });

        moveXToTruck.play();
    }

    private void startPodUnloadShipToTruck(
            Pane gamePane,
            ImageView ship,
            Image emptyShipReverseImage,
            Image containerImage,
            ImageView podTruck,
            Image truckLoadRightUpperRedImage,
            ImageView craneBody,
            ImageView craneBeam,
            ImageView craneTrolley,
            Line craneWire,
            ImageView craneSpreader
    ) {

        craneBody.toFront();
        craneBeam.toFront();
        craneTrolley.toFront();
        craneWire.toFront();
        craneSpreader.toFront();

        double trolleyShipX = 1000;
        double spreaderShipX = 990;

        double spreaderHighY = 400;
        double spreaderShipLowY = 455;

        double containerShipX = 1010;
        double containerShipY = 485;

        double trolleyTruckX = 850;
        double spreaderTruckX = 840;

        double containerTruckMoveY = 335;
        double spreaderTruckLowY = 385;
        double containerTruckX = 835;
        double containerTruckY = 405;

        ImageView unloadingContainer = new ImageView(containerImage);
        unloadingContainer.setFitWidth(SINGLE_CONTAINER_WIDTH);
        unloadingContainer.setPreserveRatio(true);
        unloadingContainer.setLayoutX(containerShipX);
        unloadingContainer.setLayoutY(containerShipY);
        unloadingContainer.setRotate(-1);
        unloadingContainer.setVisible(false);
        gamePane.getChildren().add(unloadingContainer);

        TranslateTransition trolleyToShip = new TranslateTransition(Duration.seconds(1.0), craneTrolley);
        trolleyToShip.setToX(trolleyShipX - craneTrolley.getLayoutX());
        trolleyToShip.setToY(0);

        TranslateTransition spreaderToShip = new TranslateTransition(Duration.seconds(1.0), craneSpreader);
        spreaderToShip.setToX(spreaderShipX - craneSpreader.getLayoutX());
        spreaderToShip.setToY(0);

        ParallelTransition moveToShip = new ParallelTransition(trolleyToShip, spreaderToShip);

        moveToShip.setOnFinished(e -> {
            TranslateTransition spreaderDownToShip = new TranslateTransition(Duration.seconds(0.8), craneSpreader);
            spreaderDownToShip.setToY(spreaderShipLowY - craneSpreader.getLayoutY());

            spreaderDownToShip.setOnFinished(downFinish -> {
                unloadingContainer.setVisible(true);
                unloadingContainer.toFront();
                craneSpreader.toFront();

                double containerOffsetX =
                        unloadingContainer.getLayoutX()
                                - (craneSpreader.getLayoutX() + craneSpreader.getTranslateX());
                double containerOffsetY =
                        unloadingContainer.getLayoutY()
                                - (craneSpreader.getLayoutY() + craneSpreader.getTranslateY());

                TranslateTransition spreaderUp = new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                spreaderUp.setToY(spreaderHighY - craneSpreader.getLayoutY());

                TranslateTransition containerUp = new TranslateTransition(Duration.seconds(0.8), unloadingContainer);
                containerUp.setToX(
                        craneSpreader.getLayoutX()
                                + craneSpreader.getTranslateX()
                                + containerOffsetX
                                - unloadingContainer.getLayoutX()
                                - 25
                );
                containerUp.setToY(spreaderHighY + containerOffsetY - unloadingContainer.getLayoutY() - 10);

                ParallelTransition liftFromShip = new ParallelTransition(spreaderUp, containerUp);

                liftFromShip.setOnFinished(upFinish -> {
                    TranslateTransition trolleyToTruck = new TranslateTransition(Duration.seconds(1.6), craneTrolley);
                    trolleyToTruck.setToX(trolleyTruckX - craneTrolley.getLayoutX());
                    trolleyToTruck.setToY(-75);

                    TranslateTransition spreaderToTruck = new TranslateTransition(Duration.seconds(1.6), craneSpreader);
                    spreaderToTruck.setToX(spreaderTruckX - craneSpreader.getLayoutX());
                    spreaderToTruck.setToY(-75);

                    TranslateTransition containerToTruck = new TranslateTransition(Duration.seconds(1.6), unloadingContainer);
                    containerToTruck.setToX(containerTruckX - unloadingContainer.getLayoutX());
                    containerToTruck.setToY(containerTruckMoveY - unloadingContainer.getLayoutY());

                    ParallelTransition moveToTruck =
                            new ParallelTransition(trolleyToTruck, spreaderToTruck, containerToTruck);

                    moveToTruck.setOnFinished(yardMoveFinish -> {
                        TranslateTransition spreaderDownToYard = new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                        spreaderDownToYard.setToY(spreaderTruckLowY - craneSpreader.getLayoutY());

                        TranslateTransition containerDownToYard = new TranslateTransition(Duration.seconds(0.8), unloadingContainer);
                        containerDownToYard.setToY(containerTruckY - unloadingContainer.getLayoutY());

                        ParallelTransition lowerToTruck =
                                new ParallelTransition(spreaderDownToYard, containerDownToYard);

                        lowerToTruck.setOnFinished(lowerFinish -> {
                            unloadingContainer.setVisible(false);
                            podTruck.setImage(truckLoadRightUpperRedImage);
                            podTruck.toFront();
                            ship.setImage(emptyShipReverseImage);
                            podUnloaded = true;

                            TranslateTransition spreaderBackUp =
                                    new TranslateTransition(Duration.seconds(0.8), craneSpreader);
                            spreaderBackUp.setToY(-55);
                            spreaderBackUp.play();
                        });

                        lowerToTruck.play();
                    });

                    moveToTruck.play();
                });

                liftFromShip.play();
            });

            spreaderDownToShip.play();
        });

        moveToShip.play();
    }

    public static void main(String[] args) {
        launch();
    }
}

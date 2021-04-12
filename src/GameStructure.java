import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class GameStructure {
    private static int level = 1;
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();
    private EventHandler<ActionEvent> handler;
    private int numOfAsteroids = 70;
    private int lowSpeed = 5;
    LifeHeart lifeHeart = new LifeHeart(3);



    public GameStructure(Pane playRoot, Carl carl, Random rand, Timeline t) throws FileNotFoundException {
        asteroids.clear();

        playRoot.getChildren().add(lifeHeart.getGraphic());


        for(int i = 0; i < numOfAsteroids; i++){
            asteroids.add(new Asteroid(rand.nextInt(10000-1500)+1500,rand.nextInt(720)));
        }

        for(Asteroid a : asteroids){
            playRoot.getChildren().add(a.getGraphic());
        }

        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int speed = rand.nextInt(15-lowSpeed)+lowSpeed;
                carl.draw();
                for(int i = 0; i < asteroids.size(); i++){
                    asteroids.get(i).draw();
                    asteroids.get(i).move(speed);
                    if(asteroids.get(i).getXPos() < -100){
                        playRoot.getChildren().remove(asteroids.get(i).getGraphic());
                        asteroids.remove(i);
                    }

                }

                //System.out.println(asteroids.size());
                checkLevelEnd();

            }
        };



    }

    public void checkLevelEnd(){
        System.out.println(lifeHeart.getLives());
        if(asteroids.size() == 0){
            level++;
            System.exit(0);
        }
        if(lifeHeart.getLives() == 0){
            System.exit(0);
        }
    }

    public int getLevel(){
        return level;
    }

    public EventHandler<ActionEvent> getHandler(){
        return handler;
    }

    public LifeHeart getLifeHeart(){
        return lifeHeart;
    }


}
package DartGameFirst;

public class DartGame {
    private int firstShooterPoints = 501;
    private int secondShooterPoints = 501;


    public int getFirstShooterPoints() {
        return firstShooterPoints;
    }

    public int getSecondShooterPoints() {
        return secondShooterPoints;
    }

    public void shoot(int points, int multiplier, int playerTurn) {
        validateData(points, multiplier);
        if(playerTurn == 1) {
            firstShooterPoints = countPoints(points, multiplier, getFirstShooterPoints());
            if(isWinner(getFirstShooterPoints())) {
                System.out.println("First player is won!");
            }
        }
        else {
            secondShooterPoints = countPoints(points, multiplier, getSecondShooterPoints());
            System.out.println(secondShooterPoints);
            if(isWinner(getSecondShooterPoints())) {
                System.out.println("Second player is won!");
            }
        }
    }

    private int countPoints(int value, int multiplier, int points) {
        points -= value * multiplier;
        if(points == 0) {
            return 1000;
        }
        if(points < 0) {
            return points + (value * multiplier);
        }
        return points;
    }

    private boolean isWinner(int points) {
        return (points == 1000) ? true : false;
    }

    private void validateData(int points, int multiplier) throws IllegalArgumentException {
        if(points < 0 || points > 20) {
            throw new IllegalArgumentException("Illegal value of points!");
        }
        if(multiplier != 1 && multiplier != 2 && multiplier != 3) {
            throw new IllegalArgumentException("Illegal value of multiplier!");
        }
    }
}
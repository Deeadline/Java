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
        sampleShoot(points, multiplier, playerTurn);
        checkWinner();
    }
    private void sampleShoot(int points, int multiplier, int playerTurn){
        if (playerTurn == 1) {
            firstShooterPoints = countPoints(points, multiplier, getFirstShooterPoints());
        } else {
            secondShooterPoints = countPoints(points, multiplier, getSecondShooterPoints());
        }
    }
    private int countPoints(int value, int multiplier, int points) {
        points -= value * multiplier;
        return points;
    }

    private void checkWinner() {
        if (firstShooterPoints == 0)
            System.out.println("First player won!");
        if (secondShooterPoints == 0)
            System.out.println("Second player won!");
    }

    private void validateData(int points, int multiplier) throws IllegalArgumentException {
        if (points < 0 || points > 20)
            throw new IllegalArgumentException("Illegal value of points!");
        if (multiplier != 1 && multiplier != 2 && multiplier != 3)
            throw new IllegalArgumentException("Illegal value of multiplier!");
    }
}
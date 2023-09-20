package JohnWick;

import robocode.*;

public class JohnWick extends AdvancedRobot {

    public void run() {
        setAdjustRadarForGunTurn(true); // Mantenha o radar independente do canhão
        setAdjustGunForRobotTurn(true); // Mantenha o canhão independente do movimento do robô
        
        // Defina a cor do corpo do robô como preta
        setBodyColor(java.awt.Color.black);

        while (true) {
            turnRadarRight(360); // Gire o radar em um círculo completo
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        // Quando um robô inimigo é detectado
        double enemyBearing = event.getBearing();
        double enemyDistance = event.getDistance();

        // Mantenha o radar focado no inimigo
        setTurnRadarRight(getHeading() - getRadarHeading() + enemyBearing);

        // Vire o canhão para mirar no inimigo
        setTurnGunRight(getHeading() - getGunHeading() + enemyBearing);

        // Calcule a potência de tiro com base na distância
        double power = Math.min(3, 400 / enemyDistance);
        
        // Atire no inimigo
        fire(power);
    }

    public void onHitByBullet(HitByBulletEvent event) {
        // Quando o robô é atingido por uma bala, faça uma ação evasiva
        setAhead(100); // Mova-se para frente
        setTurnRight(90); // Gire 90 graus para a direita
    }

    public void onHitWall(HitWallEvent event) {
        // Quando o robô colide com uma parede, faça uma ação evasiva
        setBack(100); // Volte 100 pixels
        setTurnLeft(90); // Gire 90 graus para a esquerda
    }
}

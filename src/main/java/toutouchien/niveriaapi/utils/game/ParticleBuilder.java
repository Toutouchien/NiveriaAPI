package toutouchien.niveriaapi.utils.game;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.joml.Vector3d;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ParticleBuilder {

    private final Collection<Player> targets;
    private Particle particle = Particle.FLAME;
    private int count = 1;
    private double offsetX = 0, offsetY = 0, offsetZ = 0;
    private double speed = 0;
    private Object data = null;
    private boolean force = false;

    private ParticleBuilder(Collection<Player> targets) {
        this.targets = targets;
    }

    public static ParticleBuilder player(Player player) {
        return new ParticleBuilder(Collections.singletonList(player));
    }

    public static ParticleBuilder players(Collection<Player> players) {
        return new ParticleBuilder(players);
    }

    public static ParticleBuilder allPlayers() {
        return new ParticleBuilder((Collection<Player>) Bukkit.getOnlinePlayers());
    }

    public ParticleBuilder particle(Particle particle) {
        this.particle = particle;
        return this;
    }

    public ParticleBuilder count(int count) {
        this.count = count;
        return this;
    }

    public ParticleBuilder offset(double x, double y, double z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        return this;
    }

    public ParticleBuilder speed(double speed) {
        this.speed = speed;
        return this;
    }

    public ParticleBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public ParticleBuilder force(boolean force) {
        this.force = force;
        return this;
    }

    // --- Core spawn method ---
    private void spawn(Vector3d vec) {
        ParticleUtils.spawnParticleForPlayers(
                targets,
                new Vector3d(vec.x(), vec.y(), vec.z()),
                particle,
                count,
                offsetX,
                offsetY,
                offsetZ,
                speed,
                data,
                force
        );
    }

    // --- Drawing methods (wrappers around ParticleUtils) ---
    public void drawLine(Location start, Location end, double spacing) {
        ParticleUtils.drawLine(start, end, particle, spacing, count, offsetX, offsetY, offsetZ, speed);
    }

    public void drawCircle(Location center, double radius, int points) {
        ParticleUtils.drawCircle(center, particle, radius, points, offsetX, offsetY, offsetZ, speed);
    }

    public void drawVerticalCircle(Location center, double radius, int points, Vector normal) {
        ParticleUtils.drawVerticalCircle(center, particle, radius, points, normal, offsetX, offsetY, offsetZ, speed);
    }

    public void drawSphere(Location center, double radius, int rings, int pointsPerRing) {
        ParticleUtils.drawSphere(center, particle, radius, rings, pointsPerRing, offsetX, offsetY, offsetZ, speed);
    }

    public void drawHelix(Location start, Vector direction, double radius, double height, int turns, int pointsPerTurn) {
        ParticleUtils.drawHelix(start, direction, particle, radius, height, turns, pointsPerTurn, offsetX, offsetY, offsetZ, speed);
    }

    public void drawSpiral(Location center, double startRadius, double endRadius, double height, int turns, int pointsPerTurn) {
        ParticleUtils.drawSpiral(center, particle, startRadius, endRadius, height, turns, pointsPerTurn, offsetX, offsetY, offsetZ, speed);
    }

    public void drawVortex(Location center, Vector direction, double startRadius, double endRadius, double length, int turns, int pointsPerTurn) {
        ParticleUtils.drawVortex(center, direction, particle, startRadius, endRadius, length, turns, pointsPerTurn, offsetX, offsetY, offsetZ, speed);
    }

    public void drawWave(Location center, double length, double width, double amplitude, int waves, int pointsPerWave) {
        ParticleUtils.drawWave(center, particle, length, width, amplitude, waves, pointsPerWave, offsetX, offsetY, offsetZ, speed);
    }

    public void drawPolygon(Location center, double radius, int sides) {
        ParticleUtils.drawPolygon(center, particle, radius, sides, offsetX, offsetY, offsetZ, speed);
    }

    public void drawStar(Location center, double outerRadius, double innerRadius, int points) {
        ParticleUtils.drawStar(center, particle, outerRadius, innerRadius, points, offsetX, offsetY, offsetZ, speed);
    }

    public void drawCube(Location corner1, Location corner2, double spacing) {
        ParticleUtils.drawCube(corner1, corner2, particle, spacing, offsetX, offsetY, offsetZ, speed);
    }

    public BukkitTask followEntity(org.bukkit.entity.Entity entity, Plugin plugin, long durationTicks, long interval) {
        return ParticleUtils.followEntity(entity, plugin, particle, count, offsetX, offsetY, offsetZ, speed, durationTicks, interval);
    }

    public void drawParametricCurve(Location center, Function<Double, Vector> parametricFunction, double tStart, double tEnd, double tStep) {
        ParticleUtils.drawParametricCurve(center, particle, parametricFunction, tStart, tEnd, tStep, offsetX, offsetY, offsetZ, speed);
    }

    public void animateParticles(Plugin plugin, List<Location> frames, long ticksPerFrame, long durationTicks, boolean loop) {
        ParticleUtils.animateParticles(plugin, frames, particle, count, offsetX, offsetY, offsetZ, speed, ticksPerFrame, durationTicks, loop);
    }

    public void drawGradient(Location start, Location end, int steps, Function<Double, Color> colorFunction) {
        ParticleUtils.drawGradient(start, end, steps, colorFunction, offsetX, offsetY, offsetZ, speed);
    }

    public void at(Vector3d vec) {
        spawn(vec);
    }
}
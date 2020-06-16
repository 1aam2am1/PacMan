public enum Node {
    BLANK(0),
    DOT(1),
    WALL(2),
    PACMAN(3),
    GHOST(4),
    POWER_UP(5);

    public final int v;

    Node(int label) {
        this.v = label;
    }
}

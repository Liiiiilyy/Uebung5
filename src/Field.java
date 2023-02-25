/**
 * Diese Klasse repräsentiert ein Spielfeld. Ihr Konstruktor bekommt dieses als
 * String-Array übergeben.
 *
 * @author Thomas Röfer
 */

/**
 * 此类表示一个游戏场地。它的构造函数将其作为字符串数组传递。
 *
 * 作者：Thomas Röfer
 */
class Field
{
    /**
     * Die Dateinamen der Bodengitterelemente, die direkt mit einer
     * Rotation 0 verwendet werden können. Der Index ergibt sich
     * aus der Summe der folgenden Zahlen:
     * 1: In Richtung 0 (+1, 0) gibt es eine Verbindung.
     * 2: In Richtung 1 (0, +1) gibt es eine Verbindung.
     * 4: In Richtung 2 (-1, 0) gibt es eine Verbindung.
     * 8: In Richtung 3 (0, -1) gibt es eine Verbindung.
     */

    /**
     * 可以直接使用旋转0的地面网格元素的文件名。索引由以下数字的总和确定：
     * 1：在0方向（+1，0）有连接。
     * 2：在1方向（0，+1）有连接。
     * 4：在2方向（-1，0）有连接。
     * 8：在3方向（0，-1）有连接。
     */
    private static final String[] NEIGHBORHOOD_TO_FILENAME = {
            "grass",
            "path-e-0",
            "path-e-1",
            "path-l-0",
            "path-e-2",
            "path-i-0",
            "path-l-1",
            "path-t-1",
            "path-e-3",
            "path-l-3",
            "path-i-1",
            "path-t-0",
            "path-l-2",
            "path-t-3",
            "path-t-2",
            "path-x"
    };

    /**
     * Die Feldbeschreibung. Jede zweite Spalte und Zeile enthält die
     * eigentlichen Zellen. Dazwischen sind die Nachbarschaften
     * vermerkt.
     */

    /**
     * 场地描述。每两个列和行包含实际单元格。其中间记录了邻域信息。
     */
    private final String[] field;

    /**
     * Erzeugt ein neues Feld.
     * @param field Die Feldbeschreibung. Jede zweite Spalte und Zeile
     *         enthält die eigentlichen Zellen. Dazwischen sind die
     *         Nachbarschaften vermerkt.
     */

    /**
     * 创建一个新场地。
     * @param field 场地描述。每两个列和行包含实际单元格。其中间记录了邻域信息。
     */
    Field(final String[] field)
    {
        this.field = field;

        for (int y = 0; y < field.length; y += 2) {
            for (int x = 0; x < field[y].length(); x += 2) {
                new GameObject(x / 2, y / 2, 0, NEIGHBORHOOD_TO_FILENAME[getNeighborhood(x, y)]);
            }
        }
    }

    /**
     * Liefert ein Zeichen der Feldbeschreibung.
     * @param x Die horizontale Koordinate des Zeichens, das
     *         zurückgeliefert wird.
     * @param y Die vertikale Koordinate des Zeichens, das
     *         zurückgeliefert wird.
     * @return Das Zeichen an der entsprechenden Zelle oder ein
     *         Leerzeichen, wenn die Koordinaten außerhalb der
     *         Beschreibung liegen.
     */
    /**
     * 返回游戏板上某一格的字符。
     * @param x 横坐标
     * @param y 纵坐标
     * @return 对应格子的字符，如果坐标不在游戏板内，则返回一个空格。
     */
    private char getCell(final int x, final int y)
    {
        if (x >= 0 && y >= 0 && y < field.length && x < field[y].length()) {
            return field[y].charAt(x);
        }
        else {
            return ' ';
        }
    }

    /**
     * Liefert die Nachbarschafts-Signatur einer Zelle der
     * Feldbeschreibung zurück.
     * @param x Die horizontale Koordinate der Zelle, deren
     *         Nachbarschafts-Signatur zurückgeliefert wird.
     * @param y Die vertikale Koordinate der Zelle, deren
     *         Nachbarschafts-Signatur zurückgeliefert wird.
     * @return Die Signatur als Summe der Zahlen 1 (x+1, y),
     *         2 (x, y+1), 4 (x-1, y) und 8 (x, y-1), wenn in
     *         der jeweilen Richtung eine Verbindung zum Nachbarn
     *         besteht.
     */
    /**
     * 返回某一格的邻居的签名。签名是一个数字，它的值是4个数（1，2，4和8）的和，
     * 分别表示向右，向下，向左和向上是否有连接。
     * @param x 横坐标
     * @param y 纵坐标
     * @return 签名
     *      */
    private int getNeighborhood(final int x, final int y)
    {
        // 分别表示每个方向的偏移量
        final int[][] neighbors = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        // 初始邻居标志位为空
        int neighborhood = 0;

        // 用于计算标志位的变量
        int bit = 1;

        // 计算标志位并返回
        for (final int[] offsets : neighbors) {
            if (getCell(x + offsets[0], y + offsets[1]) != ' ') {
                neighborhood += bit;
            }
            bit *= 2;   //由feild数组的排序决定的
        }
        return neighborhood;
    }

    /** Ein Testfall, der alle Nachbarschaften enthält. */
    public static void main(String[] args)
    {
        new GameObject.Canvas(5, 5, 96, 96);
        new Field(new String[] {
            "O        ",
            "|   |    ",
            "O O-O-O O",
            "| | | | |",
            "O-O-O-O-O",
            "| | | | |",
            "O O-O-O O",
            "    |   |",
            "O-O-O-O-O"
        });
    }
}

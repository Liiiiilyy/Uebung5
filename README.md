# java/Uebung5

## 任务1：

- 补充Field类的一个构造函数，该函数接收这样的描述，并保存该描述。为了方便访问，编写一个方法char getCell(int, int)，该方法接收x坐标（水平方向）和y坐标（垂直方向），并返回存储在该字段中的字符。如果坐标对超出了地图范围，则应返回空格（' '）。请注意，不是所有行的长度都相同。

```java
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
```

- 任务2：
现在编写一个方法`int getNeighborhood(int, int)`，该方法接收x坐标（水平方向）和y坐标（垂直方向），并返回该单元格的`flag`。该`flag`是通过为每个被占用的邻居（即不是空格的单元格）添加数字来计算的。分别是1表示(x+1, y)的邻居，2表示(x, y+1)的邻居，4表示(x-1, y)的邻居和8表示(x, y-1)的邻居。因此，结果将始终是介于0和15之间的数字。
- `neighborhood`
    - 1: (x+1, y)  ➡     0001
    - 2: (x, y+1)  ⬇     0010
    - 3: 1 + 2    ➡⬆  0011
    - 4: (x-1, y)   ⬅     0100
    - 8: (x, y-1)   ⬆    1000
    - 15                       1111
- ⚠⚠⚠
- 这个题的误区是，很容易认为`GameObject`的选择是由`getcell()` 得到的特定字符(`O,|,-`)来判断的，但实际上我们的判断条件**[并根据四周**是否有字符](https://github.com/Liiiiilyy/Uebung5/blob/bffe80c56adfb7915396f7f40bd71bce2ab70f0e/src/Field.java#L142)来选择图像（`GameObject`）。

```java
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
```

- 任务3：
现在扩展构造函数，使其通过游戏地图描述以两个步长为单位遍历，并计算`sign`。使用该签名作为索引来构建GameObject对象，并使用它们的坐标除以二。请注意，您需要检查描述中每一行的长度以确定如何正确遍历地图。

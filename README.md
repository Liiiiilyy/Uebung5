# java/Uebung5

这是一道编程题，要求对提供的Field类进行扩展，使其可以通过构造函数接收以下类型的描述，并从中生成一个游戏关卡的地图：
1 new String [] {
2 "O-O-O-O ",
3 "| | ",
4 "O O-O-O O",
5 "| | | | |",
6 "O-O-O-O-O",
7 "| | | | |",
8 "O O-O-O O",
9 " | |",
10 "O-O-O-O-O"
11 }

任务1：
补充Field类的一个构造函数，该函数接收这样的描述，并保存该描述。为了方便访问，编写一个方法char getCell(int, int)，该方法接收x坐标（水平方向）和y坐标（垂直方向），并返回存储在该字段中的字符。如果坐标对超出了地图范围，则应返回空格（' '）。请注意，不是所有行的长度都相同。

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

任务2：
现在编写一个方法int getNeighborhood(int, int)，该方法接收x坐标（水平方向）和y坐标（垂直方向），并返回该单元格的SIGN。该签名是通过为每个被占用的邻居（即不是空格的单元格）添加数字来计算的。分别是1表示(x+1, y)的邻居，2表示(x, y+1)的邻居，4表示(x-1, y)的邻居和8表示(x, y-1)的邻居。因此，结果将始终是介于0和15之间的数字。

- ⚠⚠⚠
- 这个题的误区是，很容易认为`GameObject`的选择是由`getcell()` 得到的特定字符(`O,|,-`)来判断的，但实际上我们的判断条件[**只是看当前是否有字符](https://github.com/Liiiiilyy/Uebung5/blob/bffe80c56adfb7915396f7f40bd71bce2ab70f0e/src/Field.java#L142)，并根据四周**`neighborhood` 是否有字符来选择图像（`GameObject`）。

任务3：
现在扩展构造函数，使其通过游戏地图描述以两个步长为单位遍历，并计算邻域签名。使用该签名作为索引来构建GameObject对象，并使用它们的坐标除以二。请注意，您需要检查描述中每一行的长度以确定如何正确遍历地图。

package graph;

import base.Position;

/**
 * Description:
 * ===== Edge Interface of a Graph =====
 *
 * @Author: dong
 * @Date: 2017-08-18
 * @Time: 21:59
 */
public interface Edge {
    int UNKNOWN = 0;//未知边
    int TREE = 1;//树边
    int CROSS = 2;//横跨边
    int FORWARD = 3;//前向跨边
    int BACKWARD = 4;//后向跨边

    //回当前边的信息（对于带权图，也就是各边的权重）
    int getWeight();

    //将当前边的信息更新为x，并返回原先的信息
    void setWeight(int weight);

    //取当前边在所属的图的边集E中的位置
    Position getEPosInE();

    //取v[i]在顶点集V中的位置（i=0或1，分别对应于起点、终点）
    Position getVPosInV(int i);

    //当前边在其两个端点的关联边集I(v[i])中的位置
    Position getEPosInI(int i);

    //读取、设置边的类别（针对遍历）
    int getType();

    int setType(int t);
}

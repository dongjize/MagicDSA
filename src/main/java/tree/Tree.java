package tree;

/*
 * Description: Tree interface
 *
 * The number of tree node equals the number of tree edge plus 1
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 15:48
 */
public interface Tree<E> {
    E getElem();

    void setElem(E e);

    /**
     * get the parent node of current node
     *
     * @return
     */
    LinkedListTree getParent();

    /**
     * get the first child node of current node
     *
     * @return
     */
    LinkedListTree getFirstChild();

    /**
     * get the next sibling node of current node
     *
     * @return
     */
    LinkedListTree getNextSibling();

    /**
     * get the size of child node
     *
     * @return
     */
    int getSize();

    /**
     * get the height of current node
     *
     * @return
     */
    int getHeight();

    /**
     * get the depth of current node
     *
     * depth >= 0. Tree Root has a depth of 0
     *
     * @return
     */
    int getDepth();
}

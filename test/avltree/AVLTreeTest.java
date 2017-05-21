/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import java.util.SortedSet;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void height() throws Exception {
        AVLTree tree = new AVLTree();
        tree.root = tree.add(tree.root, 1);
        tree.root = tree.add(tree.root, 20);
        tree.root = tree.add(tree.root, 3);

        //  3
        // 1  20


        assertEquals(tree.height(tree.root), 2);

        tree.root = tree.add(tree.root, 4);
        tree.root = tree.add(tree.root, 19);
        tree.root = tree.add(tree.root, 3); //уже есть, не изменит высоту

        //   3
        // 1   19
        //  4    20


        assertEquals(tree.height(tree.root), 3);


    }

    @org.junit.Test
    public void getSortedSet() throws Exception {

        AVLTree tree = new AVLTree();
        tree.root = tree.add(tree.root, 1);
        tree.root = tree.add(tree.root, 20);
        tree.root = tree.add(tree.root, 3);



        tree.root = tree.add(tree.root, 4);
        tree.root = tree.add(tree.root, 19);
        tree.root = tree.add(tree.root, 3);


        SortedSet<Integer> r = tree.getSortedSet(tree.root);
        assertEquals((int) r.first(), 1);
        assertEquals( (int)  r.last(), 20);


    }

    @org.junit.Test
    public void add() throws Exception {
        AVLTree tree = new AVLTree();
        tree.root = tree.add(tree.root, 1); //+1
        tree.root = tree.add(tree.root, 20); //+1
        tree.root = tree.add(tree.root, 3); //+1
        assertEquals(tree.countElements(tree.root), 3); //Проверяем на количество

        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 0); //Проверяем на балланс (AVL), ветви должны быть одинаковые


        assertTrue(tree.contains(tree.root, 3));
        assertTrue(tree.contains(tree.root, 1));
        assertFalse(tree.contains(tree.root, 9));


        tree.root = tree.add(tree.root, 1); //Уже есть
        tree.root = tree.add(tree.root, 4); //+1
        tree.root = tree.add(tree.root, 3); //Уже есть
        assertEquals(tree.countElements(tree.root), 4); //Проверяем на колимчество

        assertTrue(tree.contains(tree.root, 3));
        assertTrue(tree.contains(tree.root, 4));
        assertFalse(tree.contains(tree.root, 9));

        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1


        tree.root = tree.add(tree.root, 10);
        tree.root = tree.add(tree.root, 11);
        tree.root = tree.add(tree.root, 12);
        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 0); //Проверяем на баланс (AVL), должны ветви быть одинаковые




    }

    @org.junit.Test
    public void deleteNode() throws Exception {
        AVLTree tree = new AVLTree();
        tree.root = tree.add(tree.root, 1); //+1
        tree.root = tree.add(tree.root, 20); //+1
        tree.root = tree.add(tree.root, 3); //+1

        assertTrue(tree.contains(tree.root, 3));
        tree.deleteNode(tree.root, 3);
        assertFalse(tree.contains(tree.root, 3));

        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 1);//Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(tree.root), 2); //Проверяем на колимчество

        tree.root = tree.add(tree.root, 10);
        tree.root = tree.add(tree.root, 11);
        tree.root = tree.add(tree.root, 12);

        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        tree.deleteNode(tree.root, 10);
        assertEquals( Math.abs(tree.ifBalanced(tree.root)), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(tree.root), 4); //Проверяем на колимчество


    }

}

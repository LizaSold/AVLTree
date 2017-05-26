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
        tree.add(1);
        tree.add(20);
        tree.add(3);

        //  3
        // 1  20


        assertEquals(tree.height(), 2);

        tree.add(4);
        tree.add(19);
        tree.add(3); //уже есть, не изменит высоту

        //   3
        // 1   19
        //  4    20


        assertEquals(tree.height(), 3);


    }

    @org.junit.Test
    public void getSortedSet() throws Exception {

        AVLTree tree = new AVLTree();
        tree.add(1);
        tree.add(20);
        tree.add(3);



        tree.add(4);
        tree.add(19);
        tree.add(3);


        SortedSet<Integer> r = tree.getSortedSet();
        assertEquals((int) r.first(), 1);
        assertEquals( (int)  r.last(), 20);


    }

    @org.junit.Test
    public void add() throws Exception {
        AVLTree tree = new AVLTree();
        tree.add(1); //+1
        tree.add(20); //+1
        tree.add(3); //+1
        assertEquals(tree.countElements(), 3); //Проверяем на количество

        assertEquals( Math.abs(tree.ifBalanced()), 0); //Проверяем на баланс (AVL), должны ветви быть одинаковые


        assertTrue(tree.contains(3));
        assertTrue(tree.contains(1));
        assertFalse(tree.contains(9));


        tree.add(1); //Уже есть
        tree.add(4); //+1
        tree.add(3); //Уже есть
        assertEquals(tree.countElements(), 4); //Проверяем на количество

        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertFalse(tree.contains(9));

        assertEquals( Math.abs(tree.ifBalanced()), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1


        tree.add(10);
        tree.add(11);
        tree.add(12);
        assertEquals( Math.abs(tree.ifBalanced()), 0); //Проверяем на баланс (AVL), должны ветви быть одинаковые




    }

    @org.junit.Test
    public void deleteNode() throws Exception {
        AVLTree tree = new AVLTree();
        tree.add(1); //+1
        tree.add(20); //+1
        tree.add(3); //+1

        assertTrue(tree.contains(3));
        tree.deleteNode(3);
        assertFalse(tree.contains(3));

        assertEquals( Math.abs(tree.ifBalanced()), 1);//Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(), 2); //Проверяем на количество

        tree.add(10);
        tree.add(11);
        tree.add(12);

        assertEquals( Math.abs(tree.ifBalanced()), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        tree.deleteNode(10);
        assertEquals( Math.abs(tree.ifBalanced()), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(), 4); //Проверяем на количество


    }

}
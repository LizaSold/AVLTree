/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test
    public void size() throws Exception {
        AVLTree tree = new AVLTree();
        assertTrue(tree.size()==0);
        tree.add(1);
        tree.add(20);
        tree.add(3);
        assertTrue(tree.size()==3);
        tree.remove(1);
        assertTrue(tree.size()==2);

    }

    @Test
    public void isEmpty() throws Exception {
        AVLTree tree = new AVLTree();
        assertTrue(tree.size()==0);
        assertTrue(tree.isEmpty());
        tree.add(20);
        assertFalse(tree.isEmpty());
        tree.remove(20);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        AVLTree tree = new AVLTree();

        tree.add(1);
        tree.add(20);
        tree.add(3);

        assertTrue(tree.contains(1));
        assertFalse(tree.contains(2));

        tree.remove(1);
        assertFalse(tree.contains(1));

    }

    @Test
    public void iterator() throws Exception {
        AVLTree tree = new AVLTree();
        Iterator<Integer> it = tree.iterator();
        assertFalse(it.hasNext());

        tree.add(2);
        assertTrue(it.hasNext());
        assertEquals(it.next(), (Integer) 2);
        assertFalse(it.hasNext());



    }

    @Test
    public void toArray() throws Exception {
        AVLTree tree = new AVLTree();

        tree.add(1);
        tree.add(20);
        tree.add(3);
        tree.add(1); //уже есть

        Object[] r =tree.toArray();
        assertTrue(r.length==tree.size());
        for(int i=0; i < tree.size(); i++){ //Что есть все элементы
            assertTrue(tree.contains(r[i]));
        }
    }



    @Test
    public void containsAll() throws Exception {
        AVLTree tree = new AVLTree();
        tree.add(1);
        tree.add(22);
        tree.add(4);

        Collection aryLst = new ArrayList();
        aryLst.add(1);
        aryLst.add(22);
        aryLst.add(4);

        Collection aryLst2 = new ArrayList();
        aryLst2.add(1);
        aryLst2.add(3);


        assertTrue(tree.containsAll(aryLst));
        assertFalse(tree.containsAll(aryLst2));


    }

    @Test
    public void addAll() throws Exception {
        AVLTree tree = new AVLTree();

        Collection aryLst = new ArrayList();
        aryLst.add(1);
        aryLst.add(22);
        aryLst.add(4);

        tree.addAll(aryLst);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(4));

        assertTrue(aryLst.size()==tree.size());

    }

    @Test
    public void retainAll() throws Exception {
        AVLTree tree = new AVLTree();
        Collection aryLst = new ArrayList();
        aryLst.add(1);
        aryLst.add(22);
        aryLst.add(4);

        tree.addAll(aryLst);
        tree.add(44);

        tree.retainAll(aryLst);

        assertFalse(tree.contains(44));
        assertTrue(tree.contains(22));

    }

    @Test
    public void removeAll() throws Exception {
        AVLTree tree = new AVLTree();
        Collection aryLst = new ArrayList();
        aryLst.add(1);
        aryLst.add(22);
        aryLst.add(4);

        tree.addAll(aryLst);
        tree.add(44);

        tree.removeAll(aryLst);

        assertTrue(tree.contains(44));
        assertFalse(tree.contains(22));

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
    public void remove() throws Exception {
        AVLTree tree = new AVLTree();
        tree.add(1); //+1
        tree.add(20); //+1
        tree.add(3); //+1

        assertTrue(tree.contains(3));
        tree.remove(3);
        assertFalse(tree.contains(3));

        assertEquals( Math.abs(tree.ifBalanced()), 1);//Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(), 2); //Проверяем на количество

        tree.add(10);
        tree.add(11);
        tree.add(12);

        assertEquals( Math.abs(tree.ifBalanced()), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        tree.remove(10);
        assertEquals( Math.abs(tree.ifBalanced()), 1); //Проверяем на баланс (AVL), должна одна ветвь быть длинее на 1
        assertEquals(tree.countElements(), 4); //Проверяем на количество


    }

}
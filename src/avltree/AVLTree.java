/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
// Программа AVL дерево
// Позволяет добавлять и удалять узлы графа

class Node
{
    int key, height; //Значение и высота (высота нужна для проверки графа на признак AVL)
    Node left, right; //Левый и правый узел

    Node(int number) { key = number; height = 1; }
}



class AVLTree
{
    Node root;


    int height(Node node)
    {
        if (node == null)
            return 0;
        return node.height;
    }

    int max(int a, int b)
    {
        if(a>b) return a;
        return b;
    }

    //Печать
    void print(Node node)
    {
        if (node != null)
        {
            System.out.print(node.key + " ");
            print(node.left); //Рекурсивно печатаем
            print(node.right);
        }
    }

    void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", Math.abs(ifBalanced(n)));
            printBalance(n.right);
        }
    }

    void addToSorderdSet(SortedSet<Integer> r, Node n){
        if (n != null) {
            r.add(n.key);
            if(n.left != null){
                addToSorderdSet(r, n.left);
            }
            if(n.right != null){
                addToSorderdSet(r, n.right);
            }
        }
    }
    SortedSet<Integer> getSortedSet(Node n){
        SortedSet<Integer> r = new TreeSet<>();
        if (n != null) {
            addToSorderdSet(r, n);
        }
        return r;
    }


    // Правый поворот графа на узле y
    Node rotateRight(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;


        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Левый поворот на x
    Node rotateLeft(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Сбалансировано ил дерево
    int ifBalanced(Node node)
    {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    int countChildred(Node node){
        if(node==null) return 0;
        if( node.left != null && node.right != null){
            return 2 + countChildred(node.left) + countChildred(node.right);
        }
        if( node.left != null){
            return 1+ countChildred(node.left);
        }
        if( node.right != null){
            return 1+ countChildred(node.right);
        }
        return 0;
    }
    int countElements(Node node){
        if(node==null) return 0;
        return 1+ countChildred(node);
    }

    boolean contains(Node n, int key){
        if(n == null) return false;
        if(n.key==key){ return true; }
        return contains(n.left, key) || contains(n.right, key);
    }





    Node add(Node node, int key)
    {
        //Чтобы добавить новый элемент в avl дерево, надо сначала добавить элемент по правилам BST дерева
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = add(node.left, key);
        else if (key > node.key)
            node.right = add(node.right, key);
        else // Нельзя добавлять уже имеющиеся ключи
            return node;

        // Увеличить высоту
        node.height = 1 + max(height(node.left),
                height(node.right));

        // Сбалансировать по AVL правилам
        int balance = ifBalanced(node);


        // Влево Влево
        if (balance > 1 && key < node.left.key)
            return rotateRight(node);

        // Вправо Вправо
        if (balance < -1 && key > node.right.key)
            return rotateLeft(node);

        // Влево Вправо
        if (balance > 1 && key > node.left.key)
        {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Вправо Влево
        if (balance < -1 && key < node.right.key)
        {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }


    Node minNodeKey(Node node)
    {
        Node current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }

    Node deleteNode(Node root, int key)
    {
        // Базовая проверка
        if (root == null)
            return root;

        // Рекурсивно ищем
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else
        {
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else
            {
                Node temp = minNodeKey(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // Базовая проверка
        if (root == null)  return root;

        // Обновляем высоту
        root.height = max(height(root.left), height(root.right)) + 1;

        // Проверяем,что дерево сбалансированно
        int balance = ifBalanced(root);

        // Влево Влево
        if (balance > 1 && ifBalanced(root.left) >= 0)
            return rotateRight(root);

        // Влево Вправо
        if (balance > 1 && ifBalanced(root.left) < 0)
        {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Вправо Вправо
        if (balance < -1 && ifBalanced(root.right) <= 0)
            return rotateLeft(root);

        // Вправо Влево
        if (balance < -1 && ifBalanced(root.right) > 0)
        {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

}

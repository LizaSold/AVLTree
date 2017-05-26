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




public class AVLTree
{

    private class Node
    {
        int key, height; //Значение и высота (высота нужна для проверки графа на признак AVL)
        Node left, right; //Левый и правый узел

        Node(int number) { key = number; height = 1; }

        // Левый поворот на x
        public Node rotateLeft()
        {

            Node y = this.right;
            Node temp = y.left;
            y.left = this;
            this.right = temp;

            this.height = Math.max(height(this.left), height(this.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        // Правый поворот графа на узле y
        public Node rotateRight()
        {
            Node x = this.left;
            Node temp = x.right;
            x.right = this;
            this.left = temp;


            this.height = Math.max(height(this.left), height(this.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // Сбалансировано ил дерево
        public int ifBalanced()
        {
            if (this == null) return 0;
            return height(this.left) - height(this.right);
        }

        public int countChildred(){
            if(this==null) return 0;
            if( this.left != null && this.right != null){
                return 2 + this.left.countChildred() + this.right.countChildred();
            }
            if( this.left != null){
                return 1+ this.left.countChildred();
            }
            if( this.right != null){
                return 1+ this.right.countChildred();
            }
            return 0;
        }

        public int countElements(){
            if(this==null) return 0;
            return 1+ this.countChildred();
        }





    }


    private Node root;





    public int height(Node n)
    {
        if (n == null)
            return 0;
        return n.height;
    }

    public int height()
    {
        if (this.root == null)
            return 0;
        return height(this.root);
    }


    public int countElements(){
        return this.root.countElements();
    }
    public int ifBalanced(){
        return this.root.ifBalanced();
    }


    public void addToSorderdSet(SortedSet<Integer> r, Node n){
        if (n != null) {
            r.add(n.key);
            if(n.left != null) addToSorderdSet(r, n.left);

            if(n.right != null) addToSorderdSet(r, n.right);

        }
    }
    public SortedSet<Integer> getSortedSet(){
        SortedSet<Integer> r = new TreeSet<>();
        if (this.root != null) {
            addToSorderdSet(r, this.root);
        }
        return r;
    }

    boolean contains( int key){
       return this.contains(this.root, key);
    }

    boolean contains(Node n, int key){
        if(n == null) return false;
        if(n.key==key)  return true;
        return contains(n.left, key) || contains(n.right, key);
    }


    void add(int key){
        this.root = this.add(this.root, key);
    }


    Node add(Node node, int key)
    {
        //Чтобы добавить новый элемент в avl дерево, надо сначала добавить элемент по правилам BST дерева
        if (node == null) return (new Node(key));

        if (key < node.key)
            node.left = add(node.left, key);
        else if (key > node.key)
            node.right = add(node.right, key);
        else // Нельзя добавлять уже имеющиеся ключи
            return node;

        // Увеличить высоту
        node.height = 1 + Math.max(height(node.left),
                height(node.right));

        // Сбалансировать по AVL правилам
        int balance = node.ifBalanced();


        // Влево Влево
        if (balance > 1 && key < node.left.key)
            return node.rotateRight();

        // Вправо Вправо
        if (balance < -1 && key > node.right.key)
            return node.rotateLeft();

        // Влево Вправо
        if (balance > 1 && key > node.left.key)
        {
            node.left = node.left.rotateLeft();
            return node.rotateRight();
        }

        // Вправо Влево
        if (balance < -1 && key < node.right.key)
        {
            node.right = node.right.rotateRight();
            return node.rotateLeft();
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

    public void deleteNode(int key){
        this.root = this.deleteNode(this.root, key);
    }
    private Node deleteNode(Node root, int key)
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
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Проверяем,что дерево сбалансированно
        int balance = root.ifBalanced();

        // Влево Влево
        if (balance > 1 && root.left.ifBalanced() >= 0)
            return root.rotateRight();

        // Влево Вправо
        if (balance > 1 && root.left.ifBalanced() < 0)
        {
            root.left = root.left.rotateLeft();
            return root.rotateRight();
        }

        // Вправо Вправо
        if (balance < -1 && root.right.ifBalanced() <= 0)
            return root.rotateLeft();

        // Вправо Влево
        if (balance < -1 && root.right.ifBalanced() > 0)
        {
            root.right = root.right.rotateRight();
            return root.rotateLeft();
        }

        return root;
    }

}
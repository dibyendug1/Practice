package neustar;

import java.util.*;

/**
 * Solution class contains the implementation of the problem.
 * It takes input from the user and construct the hierarchical
 * tree and based on the user query it displays the result.
 */
public class Solution {
  // nodeMap contains list of tree nodes which are products and categories
  static HashMap<Integer, Node> nodeMap;
  // productTree contains the dependency relation among product and categories.
  // Basically it contains the edges of the hierarchical tree.
  static HashMap<Integer, ArrayList<Integer>> productTree;

  // Entry point of the problem
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    nodeMap = new HashMap<Integer, Node>();

    // Get the user input and populate nodeMap
    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      String[] elements = line.split(" ");
      int id = Integer.parseInt(elements[0]);
      Node tnode = new Node();
      tnode.setId(id);
      double price = Integer.parseInt(elements[1]);
      tnode.setOriginalPrice(price);
      tnode.setSellingPrice(price);
      tnode.setName(String.join(" ", subArray(elements, 2, elements.length)));
      nodeMap.put(id, tnode);
    }

    // create a product tree
    productTree = new HashMap<Integer, ArrayList<Integer>>();
    // initializet the tree with empty list
    for (int i = 0; i < n + 1; i++) {
      productTree.put(i, new ArrayList<Integer>());
    }

    // populate the tree with parent child relations
    for (int i = 0; i < n - 1; i++) {
      String edges = sc.nextLine();
      String[] elements = edges.split(" ");
      int parentId = Integer.parseInt(elements[0]);
      int childId = Integer.parseInt(elements[1]);
      productTree.get(parentId).add(childId);
    }

    sc.nextLine();
    // Get queries from user input
    int q = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < q; i++) {
      String queryLine = sc.nextLine();
      String[] elements = queryLine.split(" ");
      int qId = Integer.parseInt(elements[0]);
      int nodeId = Integer.parseInt(elements[1]);
      switch (qId) {
      case 1:
        int discout = Integer.parseInt(elements[2]);
        applyDiscount(nodeId, discout, qId);
        break;
      case 2:
        int amount = Integer.parseInt(elements[2]);
        applyDiscount(nodeId, amount, qId);
        break;
      case 3:
        readDiscount(nodeId);
        break;
      case 4:
        int newNodeId = Integer.parseInt(elements[2]);
        double price = Double.parseDouble(elements[3]);
        String name = String.join(" ", subArray(elements, 4, elements.length));
        addNode(nodeId, newNodeId, price, name);
        break;
      case 5:
        deleteNode(nodeId);
        break;
      }
    }
  }

  /**
   * This method will delete the entry of the requested node from the tree.
   * It will remove the edges from the tree.
   *
   * @param nodeId requested node id which the user wants to delete.
   */
  private static void deleteNode(int nodeId) {
    if (!nodeMap.containsKey(nodeId)) {
      System.out.println("Invalid ID.");
      return;
    }

    // If original price is -1 its a category otherwise its a product
    boolean category = nodeMap.get(nodeId).getOriginalPrice() == -1;
    nodeMap.remove(nodeId);
    productTree.remove(nodeId);
    deleteEdge(nodeId);
    // Output based on the type i.e category/product
    if (category) {
      System.out.println("Category deleted.");
    } else {
      System.out.println("Product deleted.");
    }
  }

  /**
   * This method will remove the edges of the tree.
   * Basiclly it removes the entry from edge lists
   *
   * @param nodeId requested node id which the user wants to delete.
   */
  private static void deleteEdge(int nodeId) {
    for (Map.Entry<Integer, ArrayList<Integer>> entry : productTree
        .entrySet()) {
      ArrayList<Integer> list = entry.getValue();
      if (list.contains(nodeId)) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
          if (list.get(i) == nodeId) {
            list.remove(i);
            size--;
          }
        }
      }
    }
  }

  /**
   * This will add the requested entry in the tree.
   * It creates an entry in the nodeMap and adds the edges in edge list.
   *
   * @param nodeId    parent node of the new node.
   * @param newNodeId requested node id which the user wants to add.
   * @param price     price of the product/category.
   * @param name      name of the product/categories.
   */
  private static void addNode(int nodeId, int newNodeId, double price,
      String name) {

    if (nodeMap.containsKey(newNodeId)) {
      if (price == -1) {
        System.out.println("Category not added.");
        return;
      } else {
        System.out.println("Product not added.");
        return;
      }
    }

    Node newNode = new Node();
    newNode.setId(newNodeId);
    newNode.setSellingPrice(price);
    newNode.setOriginalPrice(price);
    newNode.setName(name);
    nodeMap.put(newNodeId, newNode);
    productTree.get(nodeId).add(newNodeId);
    productTree.put(newNodeId, new ArrayList<Integer>());
    if (price == -1) {
      System.out.println("Category added.");
      return;
    } else {
      System.out.println("Product added.");
      return;
    }
  }

  /**
   * This method reads the product details from the tree and display.
   * If the requested node is a category it returns the product which has
   * maximum difference in original price and selling price among the child
   * products.
   * In case of a conflict, display the product with minimum id.
   *
   * @param nodeId product/category id, which user wants to display.
   */
  private static void readDiscount(int nodeId) {
    if (!(nodeMap.get(nodeId).getOriginalPrice() < 0)) {
      Node tnode = nodeMap.get(nodeId);
      System.out.println(tnode.getId() + " "
          + tnode.getName() + " "
          + (int) tnode.getOriginalPrice() + " "
          + (int) tnode.getSellingPrice());
      return;
    } else {
      Node tnode = getNode(nodeId);
      System.out.println(tnode.getId() + " "
          + tnode.getName() + " "
          + (int) tnode.getOriginalPrice() + " "
          + (int) tnode.getSellingPrice());
    }
  }

  /**
   * It returns the product which has maximum difference in original price and
   * selling price among the child products.
   * In case of a conflict, display the product with minimum id.
   *
   * @param nodeId category id, which user wants to display.
   * @return requested product.
   */
  private static Node getNode(int nodeId) {
    double max = Integer.MIN_VALUE;
    Node result = null;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    stack.push(nodeId);
    while (!stack.isEmpty()) {
      int tmpNode = stack.peek();
      double sellingPrice = nodeMap.get(tmpNode).getSellingPrice();
      double originalPrice = nodeMap.get(tmpNode).getOriginalPrice();
      double price = originalPrice - sellingPrice;
      if (price >= max) {
        Node tmp = nodeMap.get(tmpNode);
        if (result != null && price == max) {
          if (tmp.getId() < result.getId()) {
            result = tmp;
          }
        } else {
          result = tmp;
        }
        max = price;
      }
      stack.pop();
      for (int n : productTree.get(tmpNode)) {
        stack.push(n);
      }
    }
    return result;
  }

  /**
   * This method will apply the requested discount on the products.
   * If the requested node is product it will apply on that node else, It will
   * apply the discount on the all child nodes of the requested category.
   * Discount can be flat or percentage.
   *
   * @param nodeId   product/category id.
   * @param discount discount, user wants to apply.
   * @param query    query id(flat discount or percentage).
   */
  private static void applyDiscount(int nodeId, int discount, int query) {
    if (query == 1) {
      if (discount > 100) {
        System.out.println("Discount cannot be applied.");
        return;
      }
    } else if (query == 2) {
      //Find product with min selling price under this category.
      double minAmount = findMin(nodeId);
      if (discount > minAmount) {
        System.out.println("Discount cannot be applied.");
        return;
      }
    }
    // Search the child nodes in dfs way and apply the discount.
    LinkedList<Integer> stack = new LinkedList<Integer>();
    stack.push(nodeId);
    while (!stack.isEmpty()) {
      int tmpNodeId = stack.peek();
      if (!(nodeMap.get(tmpNodeId).getOriginalPrice() < 0)) {
        if (query == 1) {
          applyDiscountOnProduct(tmpNodeId, discount);
        } else if (query == 2) {
          applyFlatDiscountOnProduct(tmpNodeId, discount);
        }
      }
      stack.pop();
      for (int n : productTree.get(tmpNodeId)) {
        stack.push(n);
      }
    }
    System.out.println("Discount applied.");
  }

  /**
   * Apply percentage discount ont he product.
   *
   * @param nodeId  product id.
   * @param discout discount percentage.
   */
  private static void applyDiscountOnProduct(int nodeId, int discout) {
    Node tnode = nodeMap.get(nodeId);
    double price = tnode.getSellingPrice();
    double discountedPrice = price * (100 - discout) / 100;
    tnode.setSellingPrice(discountedPrice);
  }

  /**
   * Apply flat discount on the product.
   *
   * @param id     product id.
   * @param amount flat discount amount.
   */
  private static void applyFlatDiscountOnProduct(int id, int amount) {
    Node tnode = nodeMap.get(id);
    double price = tnode.getSellingPrice();
    double updatedPrice = price - amount;
    tnode.setSellingPrice(updatedPrice);
  }

  /**
   * Find the product with minimun selling price, user a specific category.
   *
   * @param nodeId category id.
   * @return node with minimum selling price.
   */
  private static double findMin(int nodeId) {
    double min = Integer.MAX_VALUE;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    stack.push(nodeId);
    while (!stack.isEmpty()) {
      int tmpNode = stack.peek();
      double price = nodeMap.get(tmpNode).getSellingPrice();
      if (price != -1 && price < min) {
        min = price;
      }
      stack.pop();
      for (int n : productTree.get(tmpNode)) {
        stack.push(n);
      }
    }
    return min;
  }

  /**
   * This will returns subarray
   */
  public static <T> T[] subArray(T[] array, int start, int end) {
    return Arrays.copyOfRange(array, start, end);
  }
}

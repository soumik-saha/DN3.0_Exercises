public class SinglyLinkedList {
    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        head = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if(head == null) {
            head = newNode;
        } else {
            Node current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while(current != null) {
            if(current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // When Task not found
    }

    public void traverseTasks() {
        Node current = head;
        while(current!=null) {
            System.out.println(("Task ID: " + current.task.getTaskId() + ", Name: " + current.task.getTaskName() + ", Status: " + current.task.getStatus()));
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.task.getTaskId() != taskId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        } else {
            System.out.println("Task not found.");
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addTask(new Task(1, "Learn Java", "Completed"));
        list.addTask(new Task(2, "Update Website", "In Progress"));

        list.traverseTasks();

        Task task = list.searchTask(1);
        if (task != null) {
            System.out.println("Found task: " + task.getTaskName());
        } else {
            System.out.println("Task not found.");
        }

        list.deleteTask(2);
        list.traverseTasks();
    }
}

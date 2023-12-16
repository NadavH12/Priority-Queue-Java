
public class PriorityQueue {
    int[] heapArray;
    int count = 0;

    public static void main(String[] args){
        int[] heapArray = {0, 10, 9, 8, 7, 5, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0};
        PriorityQueue PQ = new PriorityQueue(heapArray);
        PQ.insert(11);
        PQ.deleteMax();

    }

    //Blank array constructor
    PriorityQueue(int arraySize){
        this.heapArray = new int[arraySize];
    }

    //Custom array constructor
    PriorityQueue(int[] heapArray){
        this.heapArray = heapArray;
        for(int i = 1; i < heapArray.length; i++){
            if (heapArray[i] != 0) {
                count++;
            }
        }
    }

    public void insert(int newNode){
        count += 1; //Increment count field
        heapArray[count] = newNode; //Add newNode value at end of array
        swim(count); //Call swim passing index of newest value
    }


    public int deleteMax(){
        int max = heapArray[1];//Copy Max value (Max value is at root of heap)
        exchange(1, count);//swap the root node of the heap with the last node of the heap
        heapArray[count] = 0; //delete old last node of the heap

        sink(1); //sink the new root (former last node of heap)

        
        count -= 1;//decrement count because of deleted max node
        return max;//return the deleted max value
    }


    //When swim is called from insert then k = count = index of newest value
    public void swim (int k){
        int parentNodeValue = heapArray[k/2];
        int childNodeValue = heapArray[k];
        while(k > 1 && (parentNodeValue < childNodeValue)){
            exchange(k, k/2); //exchanges array values at given indices 
            k = k/2;
            parentNodeValue = heapArray[k/2];
            childNodeValue = heapArray[k];
        }
    }


    public void sink (int nodeIndexToSink){
        int leftChildNodeIndex = 2 * nodeIndexToSink;
        int rightChildNodeIndex = 2 * nodeIndexToSink + 1;
        int higherValueChildIndex = 0;

        while(leftChildNodeIndex <= count){//While the examined node has at least 1 child (1 child nodes always have left child)

            if(rightChildNodeIndex <= count && heapArray[leftChildNodeIndex] < heapArray[rightChildNodeIndex]){ // 
                higherValueChildIndex = rightChildNodeIndex;                                                    //
            }                                                                                                   //Get the index of the higher value child
            else                                                                                                //
                higherValueChildIndex = leftChildNodeIndex;                                                     //

            if(heapArray[nodeIndexToSink] < heapArray[higherValueChildIndex]){ //If examined node is less than the value of it's highest child
                exchange(nodeIndexToSink, higherValueChildIndex); //exchange the examined node with it's highest child

                nodeIndexToSink = higherValueChildIndex;  //We have exchanged our node with it's highest child. We now update the index of the node that we sunk
                leftChildNodeIndex = 2 * nodeIndexToSink; //We need this to see if we must sink our node further
                rightChildNodeIndex = 2 * nodeIndexToSink + 1;
                higherValueChildIndex = 0; //initialize new child nodes 
            }

            else //Examined node is higher than it's children, so heap is valid and sinking is complete
                break;
        }
    }


    private void exchange(int a, int b){
        int copy = heapArray[a];
        heapArray[a] = heapArray[b];
        heapArray[b] = copy;
    }
}

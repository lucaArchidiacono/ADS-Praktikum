package Praktikum_13_Code;

public class TestCObjectGenerationServer implements CommandExecutor  {

   private static CObject new_CObject(Object s) {
      CObject obj = (CObject)StorageGeneration._new("CObject",s);
      return obj;
   }

   static CObject a;
   static CObject e;

   public String execute (String input) {
     run();
     return StorageGeneration.getLog();
   }


  private void run() {
     a = new_CObject("A");
     CObject b = new_CObject("B");
     CObject c = new_CObject("C");
     CObject d = new_CObject("D");
     e = new_CObject("E");
     CObject f = new_CObject("F");
     CObject g = new_CObject("G");
     CObject h = new_CObject("H");
     StorageGeneration.addRoot(a);
     StorageGeneration.addRoot(e);
     a.next = b; b.next = c; b.down = a; c.down = d;
     e.next = f; f.next = g; g.next = e;
     StorageGeneration.dump("\nRoots:", StorageGeneration.getRoot());
     StorageGeneration.dump("Young Heap 1:", StorageGeneration.getYoungHeap());
     StorageGeneration.dump("Old Heap 1:", StorageGeneration.getOldHeap());
     StorageGeneration.gcYoungHeapOnly();
     StorageGeneration.dump("Young Heap 2:", StorageGeneration.getYoungHeap());
     StorageGeneration.dump("Old Heap 2:", StorageGeneration.getOldHeap());
     b.next = f;
     StorageGeneration.gc();
     StorageGeneration.dump("Young Heap 3:", StorageGeneration.getYoungHeap());
     StorageGeneration.dump("Old Heap 3:", StorageGeneration.getOldHeap());
     f.next = null;
     StorageGeneration.gcYoungHeapOnly();
     StorageGeneration.dump("Young Heap 4:", StorageGeneration.getYoungHeap());
     StorageGeneration.dump("Old Heap 4:", StorageGeneration.getOldHeap());
     StorageGeneration.gc();
     StorageGeneration.dump("Young Heap 5:", StorageGeneration.getYoungHeap());
     StorageGeneration.dump("Old Heap 5:", StorageGeneration.getOldHeap());
  }

}

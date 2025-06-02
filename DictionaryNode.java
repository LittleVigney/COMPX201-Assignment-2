public class DictionaryNode{
    String value;
    String def;
    DictionaryNode left;
    DictionaryNode right;

    public DictionaryNode(String _value, String _def){
        this.value = _value;
        this.def = _def;
        this.left = null;
        this.right = null;
    }
}

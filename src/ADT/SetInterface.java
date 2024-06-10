/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author star
 */
public interface SetInterface<T> {
    public boolean add(T newElement);
    public boolean remove(T newElement);
    public boolean checkSubset(SetInterface anotherSet);
    public void union(SetInterface anotherSet);
    public boolean isEmpty();
    public int getSize();
     public void clear();
    public SetInterface intersection(SetInterface anotherSet);
    public Iterator<T> iterator();
          public boolean contains(T element);
}

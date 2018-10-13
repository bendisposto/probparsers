#include <iostream>
#include <string>
#include <set>
#include <vector>
#include <cstdarg>
#include "BInteger.cpp"
#include "BCouple.cpp"

#ifndef BSET_H
#define BSET_H

using namespace std;

class BSet : public BObject {

    private:
        std::set<BObject> set;

    public:

        BSet(const std::set<BObject> elements) {
            this->set = elements;
        }

        BSet(const BObject elements[]) {
            this->set = std::set<BObject>();
            int length = sizeof(elements)/sizeof(*elements);
            for(int i = 0; i < length; i++) {
                this->set.insert(elements[i]);
            }
        }

        BSet() {
            this->set = std::set<BObject>();
        }

        BSet(const BObject& v) : BObject(v) {}

	/*public BSet(java.util.Set<BObject> elements) {
		this.set = HashTreePSet.from(elements);
	}

	public BSet(PSet<BObject> elements) {
		this.set = elements;
	}

	public BSet(BObject... elements) {
		this.set = HashTreePSet.from(Arrays.asList(elements));
	}*/

	/*public static LinkedHashSet<BObject> newStorage() {
		return new LinkedHashSet<>();
	}

	public java.lang.String toString() {
		Iterator<BObject> it = this.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		while (it.hasNext()) {
			BObject b = (BObject) it.next();
			sb.append(b.toString());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}*/

        int size() {
            return set.size();
        }

        bool isEmpty() {
            return set.empty();
        }

        bool contains(BObject o) {
            return set.count(o) != 0;
        }

        /*boolean add(BObject bObject) {
            throw new UnsupportedOperationException();
        }

        boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        void clear() {
            throw new UnsupportedOperationException();
        }*/

        /*boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            BSet bObjects = (BSet) o;

            if (!set.equals(bObjects.set))
                return false;

            return true;
        }

        int hashCode() {
            return set.hashCode();
        }

        boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        Object[] toArray() {
            return set.toArray();
        }

        <T> T[] toArray(T[] a) {
            return set.toArray(a);
        }

        boolean containsAll(Collection<?> c) {
            return set.containsAll(c);
        }

        boolean addAll(Collection<? extends BObject> c) {
            throw new UnsupportedOperationException();
        }

        boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        Iterator<BObject> iterator() {
            return set.iterator();
        }*/

        BSet intersect(BSet set) {
            std::set<BObject> result;
            for (std::set<BObject>::iterator it = set.set.begin(); it != set.set.end(); ++it) {
                BObject obj = *it;
                if(this->set.find(obj) != this->set.end()) {
                    result.insert(obj);
                }
            }
            return BSet(result);
        }

        BSet complement(BSet set) {
            std::set<BObject> result;
            for (std::set<BObject>::iterator it = this->set.begin(); it != this->set.end(); ++it) {
                BObject obj = *it;
                if(set.set.find(obj) != set.set.end()) {
                    result.insert(*it);
                }
            }
            return BSet(result);
        }

        BSet _union(BSet set) {
            std::set<BObject> result;
            for (std::set<BObject>::iterator it = this->set.begin(); it != this->set.end(); ++it) {
                result.insert(*it);
            }
            for (std::set<BObject>::iterator it = set.set.begin(); it != set.set.end(); ++it) {
                result.insert(*it);
            }
            return BSet(result);
        }

        static BSet range(BInteger a, BInteger b) {
            std::set<BObject> result;
            for(BInteger i = a; i.lessEqual(b).booleanValue(); i = (BInteger) i.next()) {
                result.insert(BInteger(i));
            }
            return BSet(result);
        }

        BSet relationImage(BSet domain) {
            std::set<BObject> result;
            for(std::set<BObject>::iterator it = this->set.begin(); it != this->set.end(); ++it) {
                BObject object = *it;
                BCouple couple = (BCouple) object;
                if(domain.set.find(couple.getFirst()) == domain.set.end()) {
                    result.insert(couple.getSecond());
                }
            }
            return BSet(result);
        }


        BObject functionCall(BObject arg) {
            for(std::set<BObject>::iterator it = this->set.begin(); it != this->set.end(); ++it) {
                BObject object = *it;
                BCouple couple = (BCouple) object;
                if(couple.getFirst() == arg) {
                    return couple.getSecond();
                }
            }
            throw runtime_error("Argument is not in the key set of this map");
        }


        BInteger card() {
            return BInteger(this->set.size());
        }

        BBoolean elementOf(BObject object) {
            return BBoolean(this->set.find(object) != this->set.end());
        }

        /*BBoolean equal(BSet o) {
            return new BBoolean(equals(o));
        }

        BBoolean unequal(BSet o) {
            return new BBoolean(!equals(o));
        }*/

};
#endif
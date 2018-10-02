#include <iostream>
#include <string>
#include <set>
#include "BObject.cpp"

using namespace std;

class BSet : public BObject, public std::set<BObject> {

    private:
        std::set<BObject> set;

    public:

        BSet(std::set<BObject> elements) {
            this->set = elements;
        }

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

        /*BSet intersect(BSet set) {
            return new BSet(this.set.plusAll(set)
                    .minusAll(this.set.minusAll(set))
                    .minusAll(set.set.minusAll(this.set)));
        }

        BSet complement(BSet set) {
            return new BSet(this.set.minusAll(set));
        }

        BSet union(BSet set) {
            return new BSet(this.set.plusAll(set));
        }

        static BSet range(BInteger a, BInteger b) {
            HashSet<BObject> set = new HashSet<>();
            for(BInteger i = a; i.lessEqual(b).booleanValue(); i = (BInteger) i.next()) {
                set.add(new BInteger(new java.math.BigInteger(String.valueOf(i))));
            }
            return new BSet(set);
        }

        BSet relationImage(BSet domain) {
            return new BSet(set.stream()
                .filter(object -> domain.contains(((BCouple) object).getFirst()))
                .map(object -> ((BCouple) object).getSecond())
                .collect(Collectors.toSet()));
        }


        BObject functionCall(BObject arg) {
            List<BCouple> matchedCouples = set.stream()
                .map(object -> (BCouple) object)
                .filter(couple -> couple.getFirst().equals(arg))
                .collect(Collectors.toList());
            if(matchedCouples.size() > 0) {
                return matchedCouples.get(0).getSecond();
            }
            throw new RuntimeException("Argument is not in the key set of this map");
        }


        BInteger card() {
            return new BInteger(String.valueOf(this.size()));
        }

        BBoolean elementOf(BObject object) {
            return new BBoolean(this.contains(object));
        }

        BBoolean equal(BSet o) {
            return new BBoolean(equals(o));
        }

        BBoolean unequal(BSet o) {
            return new BBoolean(!equals(o));
        }*/

};

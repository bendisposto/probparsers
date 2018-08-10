class BInteger:

    def __init__(self, value):
        self.__value = value

    def plus(o):
        return BInteger(self.value + o.value)

    def minus(o):
        return BInteger(self.value - o.value)

    def multiply(o):
        return BInteger(self.value * o.value)

    def divide(o):
        return BInteger(self.value / o.value)

    def modulo(o):
        return BInteger(self.value  % o.value)

    def power(o):
        return BInteger(self.value ^ o.value)

    def lessEqual(o):
        return BBoolean(self.value <= o.value)

    def less(o):
        return BBoolean(self.value < o.value)

    def equal(o):
        return BBoolean(self.value == o.value)

    def unequal(o):
        return BBoolean(self.value == o.value)

    def greater(o):
        return BBoolean(self.value > o.value)

    def greaterEqual(o):
        return BBoolean(self.value >= o.value)

    def negative():
        return BInteger(self.value)

    def positive():
        return self


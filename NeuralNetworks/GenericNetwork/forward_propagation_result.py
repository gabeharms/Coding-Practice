
class ForwardPropagationResult:
    def __init__(self):
        self.raw_result_A = {}
        self.raw_result_Z = {}

    def get_A(self, index):
        return self.raw_result_A[self.__A_string_key(index)]

    def set_A(self, index, val):
        self.raw_result_A[self.__A_string_key(index)] = val

    def get_last_A(self):
        return self.raw_result_A[self.__last_A_key()]

    def get_Z(self, index):
        return self.raw_result_Z[self.__Z_string_key(index)]

    def set_Z(self, index, val):
        self.raw_result_Z[self.__Z_string_key(index)] = val

    def get_last_Z(self):
        return self.raw_result_Z[self.__last_Z_key()]

    def __A_string_key(self, index):
        return "A%d" % index

    def __last_A_key(self):
        return list(self.raw_result_A.keys())[len(self.raw_result_A.keys())-1]

    def __Z_string_key(self, index):
        return "Z%d" % index

    def __last_Z_key(self):
        return list(self.raw_result_Z.keys())[len(self.raw_result_Z.keys())-1]





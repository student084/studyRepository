#Filename: pro_analyze.py

class analyze:
    def __init__(self, input_string, ll_table, Vn, Vt):
        self.string = input_string
        self.stack  = ['#']
        self.ll_table = ll_table
        self.vn = Vn
        self.vt = Vt
    def search_table(self, front_Vn, present_char):
        result = ""
        position_vt = self.vt.index(present_char)
        position_vn = self.vn.index(front_Vn)
        result = self.ll_table[position_vn][position_vt]
        return result
    def is_vn(self, char):
        if (char <= 'Z' and char >= 'A'):
            return True
        else:
            return  False
    def analyze_str(self, start_vn):
        self.stack.append(start_vn)
        tmp_char_position = 0
        flage = True
        print ""
        self.change_view("%30s"%self.stack),
        print "%10s"%self.string[tmp_char_position:],""
        while(flage):
            tmp_ch = self.string[tmp_char_position]
            tmp_X = self.stack.pop(-1)
            if not self.is_vn(tmp_X) and tmp_X != '#':
                if tmp_X == tmp_ch:
                    print ""
                    self.change_view("%30s"%self.stack),
                    print "%10s"%self.string[tmp_char_position:],""
                    tmp_char_position = tmp_char_position + 1
                    continue
                else:
                    print "error in string you input"
                    break
            elif tmp_X == '#':
                if tmp_X == tmp_ch:
                      print "finish analyze"
                      break;
                else:
                 #   print "error analyze"
                    flage = False
            elif not self.search_table(tmp_X, tmp_ch) == '':
                tmp_string = self.search_table(tmp_X, tmp_ch)[::-1]
                if not tmp_string == ' ':
                    for tmp_char in tmp_string:
                        self.stack.append(tmp_char)
                #print "%30s"%self.stack,
                print ""
                self.change_view("%30s"%self.stack)
                #print " ",
                print "%10s"%self.string[tmp_char_position:],
                #print tmp_X,"-->",self.search_table(tmp_X, tmp_ch)
                self.change_view("%20s"%(tmp_X+"-->"+self.search_table(tmp_X, tmp_ch)))
                print ""
                #print tmp_char
            else:
                print "error"
                break
    def change_view(self, print_string):
        for tmp_ch in print_string:
            if tmp_ch == "A":
                tmp_ch = "E'"
            if tmp_ch == "B":
                tmp_ch = "T'"
            print tmp_ch,
#main start
input_string = "i*i+i#"
Vn = "EATBF"
Vt = "i+*()#"
ll_table = [["TA","","","TA","",""],
            ["","+TA","",""," "," "],
            ["FB","","","FB","",""],
            [""," ","*FB",""," "," "],
            ["i","","","(E)","",""]]
analyze_1 = analyze(input_string, ll_table, Vn, Vt)
analyze_1.analyze_str("E")
#main end  

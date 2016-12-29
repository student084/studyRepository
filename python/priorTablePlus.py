#Filename: priorTablePlus.py

class Table:
    #each G string depart by '<-->'
    def  __init__(self, G_n, G_string):
        #example:
                # if string is "E->E+T|T" then
                # "E+T" is left_dict's element
                # "T"   is right_dirt's element
        array_string = G_string.split('<-->')
        if not len(array_string) == G_n:
            print "input G_string or G_n is wrong"
            return
        else:
            n = ""
            t = ""
            left = {}
            right = {}
            #Stack
            self.stack = []
            self.fristStack = []
            self.lastStack = []
            for item in range(0, len(array_string)):
                tmp_string = array_string[item].split('->')
                # if left Vn not in self.Vn, then insert into it
                if n.find(tmp_string[0]) == -1:
                    n = n + tmp_string[0]
                for each_ch in tmp_string[1]:
                    # 00
                    # if each_ch not in Vt ,not eq to '|', not "A"--"Z"
                    if not ((t.find(each_ch) > -1 or each_ch == "|") or ( each_ch <= 'Z' and each_ch >= 'A')):
                        t = t + each_ch
                # if left_dirt part not exit '|'
                if tmp_string[1].find('|') == -1:
                    left[tmp_string[0]]  = tmp_string[1]
                else:
                    left[tmp_string[0]]  = tmp_string[1].split('|')[0]
                    right[tmp_string[0]] = tmp_string[1].split('|')[1]
            #dict means dictionary
            self.Vn = n
            self.Vt = t + "#"
            self.left_dict = left
            self.right_dict = right
    def F(self, P, a):
        #the 'a' is exist in  dictionary
        #the struct like: P->a...
        result = False
        if (self.left_dict[P].find(a) == 0 or self.right_dict[P].find(a) == 0):
            result = True
        else:
            #the struct like:P->Qa...
            for signQ in self.Vn:
                Qa = signQ + a
                if (self.left_dict[P].find(Qa) == 0 or self.right_dict[P].find(Qa) == 0):
                    result = True
                    break
        return result
    def L(self, P, a):
        result = False
        #the struct like: P->..a
        if (self.left_dict[P].find(a) == (len(self.left_dict[P]) - 1) or self.right_dict[P].find(a) == (len(self.left_dict[P]) - 1)):
            result = True
        else:
            #the struct like: P->...aQ
            for signQ in self.Vn:
                result = False
                aQ = a + signQ
                if (self.left_dict[P].find(aQ) == (len(self.left_dict[P]) - 2) or self.right_dict[P].find(aQ) == (len(self.left_dict[P]) - 2)):
                    result =True
                    break
        return result
    def setFrist(self):
        self.stack = []
        self.fristStack = []
        #if F[P, a] == true,put the combition in to STACK
        for eachVn in self.Vn:
            for eachVt in self.Vt:
                if self.F(eachVn, eachVt):
                    workingGroup = {eachVn: eachVt} 
                    self.stack.append(workingGroup)
        #print self.F('F','!')
        #print "self.stack=",self.stack
        while len(self.stack) > 0:
            topGroup = self.stack.pop()
            self.fristStack.append(topGroup)
            Q = topGroup.items()[0][0]
            a = topGroup.items()[0][1]
            for eachP in self.Vn:
                if (self.left_dict[eachP].find(Q) == 0 or self.right_dict[eachP].find(Q) == 0):
                    P = eachP
                    #two 'if' to judge the new F[P, a] is not included in the true V
                    if not self.F(P, a) :
                        if not {P : a} in self.fristStack:
                            self.stack.append({P : a})
        return self.fristStack
    def setLast(self):
        self.stack = []
        self.lastStack = []
        for eachVn in self.Vn:
            for eachVt in self.Vt:
                if self.L(eachVn, eachVt):
                    workingGroup = {eachVn: eachVt}
                    self.stack.append(workingGroup)
        while len(self.stack) > 0:
            topGroup = self.stack.pop()
            self.lastStack.append(topGroup)
            Q = topGroup.items()[0][0]
            a = topGroup.items()[0][1]
            #struct like :P -> ...Q
            for eachP in self.Vn:
                if (self.left_dict[eachP].find(Q) == (len(self.left_dict[eachP]) - 1)) or (self.right_dict[eachP].find(Q) == (len(self.left_dict[eachP]) - 1)):
                    P = eachP
                    if not self.L(P, a) :
                        if not {P : a} in self.lastStack:
                            self.stack.append({P : a})
        return self.lastStack
    def setTable(self):
        lineCompare = 0
        #print self.left_dict
        #self.left_dict = {'P': '(E)', 'E': 'E+T', 'T': 'T*F', 'F': 'P!F'}
        #print "---" + "self.Vt = " + self.Vt
        #print "---" + "self.Vn =" + self.Vn
        #print "---" + "self.left_dict",
        #print self.left_dict
        #print "---" + "self.right_dict",
        #print self.right_dict
        #print "---" + "FristV"
        fristStr = self.setFrist()
        #print fristStr
        #print "---" + "LastV"
        lastStr = self.setLast()
        tableList = []
        #print lastStr
        for eachPrior in self.left_dict:
            XnStr = self.left_dict[eachPrior]
            #print "XnStr = " + XnStr
            #(E)
            len_Xn = len(XnStr)
            for i in range(0, len_Xn - 1):
               # print "XnStr[] = " + XnStr[i]
               # print (XnStr[i] in self.Vt)
               # print (XnStr[i + 1] in self.Vt)
                # if both X(i) and X(i+1) are Vt, then 'X(i) = X(i+1)' 
                if ((XnStr[i] in self.Vt) and (XnStr[i + 1] in self.Vt)):
                    print XnStr[i] + "=" + XnStr[i + 1]
                    tableList.append(XnStr[i] + "=" + XnStr[i + 1])
                #if i<= (n - 2) ,and both X(i) and X(i+2) are Vt
                if i < (len_Xn - 2) and (XnStr[i] in self.Vt) and (XnStr[i + 2] in self.Vt):
                    #but X(i) is Vn,then X(i) = X(i+2)
                    if (XnStr[i + 1] in self.Vn):
                        print XnStr[i] + "=" + XnStr[i + 2]
                        lineCompare += 1
                        tableList.append(XnStr[i] + "=" + XnStr[i + 2])
                #X(i) is Vt and X(i+1) is Vn, then ...
                if  (XnStr[i]  in self.Vt) and ((XnStr[i+ 1])  in self.Vn):
                    #for each FRIST(X(i+1))'s a, X(i) < a
                    for eachFrist in self.fristStack:
                        if eachFrist.items()[0][0] == XnStr[i + 1]:
                            for a in eachFrist.items()[0][1]:
                                if (a in self.Vt):
                                    print XnStr[i] + "<" + a
                                    lineCompare += 1
                                    tableList.append(XnStr[i] + "<" + a)
                #if X(i) is Vn and X(i+1) is Vt
                if (XnStr[i] in self.Vn) and (XnStr[i + 1] in self.Vt):
                    #for each LAST(X(i))'s a, a > X[i+1]
                    for eachLast in self.lastStack:
                        if eachLast.items()[0][0] == XnStr[i]:
                            for a in eachLast.items()[0][1]:
                                if (a in self.Vt):
                                    print a + ">" + XnStr[i + 1]
                                    lineCompare += 1
                                    tableList.append( a + ">" + XnStr[i + 1])
        for eachPrior in self.right_dict:
            XnStr = self.right_dict[eachPrior]
            #print "XnStr = " + XnStr
            #(E)
            len_Xn = len(XnStr)
            for i in range(0, len_Xn - 1):
               # print "XnStr[] = " + XnStr[i]
               # print (XnStr[i] in self.Vt)
               # print (XnStr[i + 1] in self.Vt)
                # if both X(i) and X(i+1) are Vt, then 'X(i) = X(i+1)' 
                if ((XnStr[i] in self.Vt) and (XnStr[i + 1] in self.Vt)):
                    print XnStr[i] + "=" + XnStr[i + 1]
                    tableList.append(XnStr[i] + "=" + XnStr[i + 1])
                #if i<= (n - 2) ,and both X(i) and X(i+2) are Vt
                if i < (len_Xn - 2) and (XnStr[i] in self.Vt) and (XnStr[i + 2] in self.Vt):
                    #but X(i) is Vn,then X(i) = X(i+2)
                    if (XnStr[i + 1] in self.Vn):
                        print XnStr[i] + "=" + XnStr[i + 2]
                        lineCompare += 1
                        tableList.append(XnStr[i] + "=" + XnStr[i + 2])
                #X(i) is Vt and X(i+1) is Vn, then ...
                if  (XnStr[i]  in self.Vt) and ((XnStr[i+ 1])  in self.Vn):
                    #for each FRIST(X(i+1))'s a, X(i) < a
                    for eachFrist in self.fristStack:
                        if eachFrist.items()[0][0] == XnStr[i + 1]:
                            for a in eachFrist.items()[0][1]:
                                if (a in self.Vt):
                                    print XnStr[i] + "<" + a
                                    lineCompare += 1
                                    tableList.append(XnStr[i] + "<" + a)
                #if X(i) is Vn and X(i+1) is Vt
                if (XnStr[i] in self.Vn) and (XnStr[i + 1] in self.Vt):
                    #for each LAST(X(i))'s a, a > X[i+1]
                    for eachLast in self.lastStack:
                        if eachLast.items()[0][0] == XnStr[i]:
                            for a in eachLast.items()[0][1]:
                                if (a in self.Vt):
                                    print a + ">" + XnStr[i + 1]
                                    lineCompare += 1
                                    tableList.append( a + ">" + XnStr[i + 1])
        for eachVt in self.Vt:
            if eachVt == '#':
                tableList.append("#=#")
            else:
                #if(eachVt == ')'):
                strAttach = "#<"+ eachVt
                tableList.append(strAttach)
        #print "lineCompare = ",lineCompare
        return tableList
    def analyse(self, string):
        VnStack = ['']*20
        k = 1
        S = [""]*20
        S[k] = "#"
        table = self.setTable()
        stringSource = string + '#'
        #print table
        #print 'i<#'
        for a in stringSource:
            doSign = True
            if doSign:
                if (S[k] in self.Vt):
                    j = k
                else:
                    j = k -1
                str1 = S[j] + ">" + a
                str2 = a + "<" + S[j]
                breakState = False
                while ((str1 in table) or (str2 in table)) and (not breakState):
                    Q = S[j]
                    if S[j - 1] in self.Vt:
                        j = j - 1
                    else:
                        j = j - 2
                    while not ((S[j] + "<" + Q) in table or (Q + ">" + S[j]) in table):
                        Q = S[j]
                        if S[j - 1] in self.Vt:
                            j = j - 1
                        else:
                            j = j - 2
                    
                    #k = j + 1
                    tmpStr = ""
                    canSimple = True
                    N = ''
                    while(canSimple):
                        for i in range(j+1, k + 1):
                            if (not S[i] == '#'):
                                tmpStr = tmpStr + S[i]
                                #print "tmpStr = ",tmpStr
                        for eachVn in self.Vn:
                            if self.makeN(tmpStr, self.right_dict[eachVn]) or self.makeN(self.left_dict[eachVn], tmpStr):
                                N = eachVn
                                k = j + 1
                                S[k] = N
                                strByS = self.listToStr(S)
                                #-----
                                #if self.makeN(strByS, self.right_dict[eachVn]) or self.makeN(self.left_dict[eachVn], strByS):
                                #-----
                                print strByS
                                resultString = strByS
                                breakState = True
                                break
                            else:
                                canSimple = False
            if ((S[j] + "<" + a) in table) or ((a + "<" + S[j]) in table) or ((S[j] + '=' + a) in table)or ((a + '=' + S[j]) in table):
                k = k + 1
                S[k] = a
            else:
                print "--error--"
                #------------------------------------
                #
            print S
        return resultString
    def makeN(self, stringA, stringB):
        result = False
        strA = ''
        strB = ''
        if (len(stringA) == len(stringB)):
            for itemA in stringA:
                if itemA in self.Vn:
                    strA = strA + 'A'
                else:
                    strA = strA + itemA
            for itemB in stringB:
                if itemB in self.Vn:
                    strB = strB + 'A'
                else:
                    strB = strB + itemB
            if strB == strA:
                result = True
        return result
    def listToStr(self, listA):
        resultStr = ''
        for item in listA:
            if (not item == '') and (not item == '#'):
                resultStr = resultStr + item
        return resultStr
    def analyseAll(self, string):
        result = string
        source = ''
        print "--->",string
        for chPosition in range(0, len(result)):
            source = source + string[chPosition]
            if len(source) > 1:
                for eachVn in self.Vn:
                    if self.makeN(source, self.right_dict[eachVn]) or self.makeN(self.left_dict[eachVn], source):
                        source = eachVn
                        print "--->",source + string[chPosition + 1:]
                        break
        return source
#Main
#Start
G_string = "E->E+T|T<-->T->T*F|F<-->F->P!F|P<-->P->(E)|i"
source = Table(4, G_string)
#print source.left_dict
#print source.right_dict
#print source.left_dict
#print "----"
#print source.right_dict
#print "----"
#source.setTable()
#print "----"
#print source.Vn
#print "----"
#print source.Vt
#print "----"
#print source.setTable()
#print source.analyse("i+i*i")
#print source.analyse("P+P*P")
source.analyseAll(source.analyse("i*i+i"))
#--------------------------------print source.analyseAll('P*P')
#print source.F('E', '+')
#print source.F('F', '!')
#print source.F('E', '*')


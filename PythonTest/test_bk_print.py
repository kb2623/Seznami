import pexpect

def test_bk_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use bk")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("2\t1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("2")
        baza.expect("Enter command: ")

        baza.send("depth")
        baza.expect("1")
        baza.expect("Enter command: ")

        print "PASSED\ttest_bk_print"

    except:
        print "FAILED\ttest_bk_print"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bk_print()


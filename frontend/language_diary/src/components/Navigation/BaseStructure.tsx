import {AppShell, Flex} from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import {NavbarNested} from "./NavbarNested";
import { Text } from '@mantine/core';
import {MainContentContainer} from "./MainContentContainer";
import {ColorSchemeToggle} from "@/components/ColorSchemeToggle/ColorSchemeToggle";

//import {HeaderMegaMenu} from "./HeaderMegaMenu.tsx";

export function BaseStructure() {
    const [opened] = useDisclosure();

    return (
        <AppShell
            padding="md"
            header={{ height: 50 }}
            navbar={{
                width: 250,
                breakpoint: 'sm',
                collapsed: { mobile: !opened },
            }}
            layout="alt"
            /*footer={{ height: 30 }}*/
        >

            <AppShell.Header c="blue" withBorder={false}>
                <Flex
                    mih={20}
                    //bg="rgba(0, 0, 0, .3)"
                    gap="md"
                    justify="flex-end"
                    align="center"
                    direction="row"
                    wrap="nowrap"
                    mr={5}
                >
                    <Text> Site is under construction! </Text>
                </Flex>


            </AppShell.Header>


            <AppShell.Navbar>
                <NavbarNested />
            </AppShell.Navbar>

            <AppShell.Main bg="transparent">
                <MainContentContainer />
            </AppShell.Main>

            {/*}
            <AppShell.Footer bg="purple"></AppShell.Footer>
            */}
        </AppShell>
    );
}
import {Container, Text} from '@mantine/core';
import {ColorSchemeToggle} from "@/components/ColorSchemeToggle/ColorSchemeToggle";

export function MainContentContainer() {
    return (
        <Container strategy="grid" size={800} c={"red"}>
            <Text> Main Content Area. </Text>
            <ColorSchemeToggle />
        </Container>
    );
}


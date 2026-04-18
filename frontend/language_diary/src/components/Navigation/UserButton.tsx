import { IconChevronRight } from '@tabler/icons-react';
import { Avatar, Group, Text, UnstyledButton } from '@mantine/core';
import classes from './UserButton.module.css';

export function UserButton() {
  return (
    <UnstyledButton className={classes.user}>
      <Group wrap="nowrap">
        <Avatar
          src="https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-8.png"
          radius="md"
          alt="Sample Name"
        />

        <div style={{ flex: 1 }}>
          <Text size="sm" fw={500}>
            Sample Name
          </Text>

          <Text c="dimmed" size="xs">
            samplename
          </Text>

        </div>

        <IconChevronRight size={14} stroke={1.5} />
      </Group>
    </UnstyledButton>
  );
}
